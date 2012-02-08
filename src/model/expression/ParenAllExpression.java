package model.expression;

import java.util.*;
import java.util.regex.*;

import model.*;

public abstract class ParenAllExpression extends Expression {

	private static String myCommand; 
	private List<Expression> subexpressions;

	protected ParenAllExpression(List<Expression> subexpressions)
	{
		this.subexpressions = subexpressions;
	}

	protected List<RGBColor> evaluateSubexpressions(HashMap<String, Expression> varMap, double evalX, double evalY, double myCurrentTime)
	{
		List<RGBColor> result = new ArrayList<RGBColor>(subexpressions.size());
		for(Expression exp : subexpressions) {
			result.add(exp.evaluate(varMap, evalX, evalY, myCurrentTime));
		}
		return result;
	}

	protected List<Expression> getSubexpressions()
	{
		return Collections.unmodifiableList(subexpressions);
	}

	public abstract static class Factory extends Expression.Factory
	{
		private static final Pattern EXPRESSION_BEGIN_REGEX =
				Pattern.compile("\\(([a-zA-Z]+)");

		protected String getCommand(Parser parser) {
			Matcher expMatcher = EXPRESSION_BEGIN_REGEX.matcher(parser.stringAtCurrentPosition());
			expMatcher.find(0);
			return expMatcher.group(1);
		}

		@Override
		public boolean isKindOfExpression(Parser parser, HashMap<String, Expression> varMap) {			
			if(!regexMatches(EXPRESSION_BEGIN_REGEX,parser))
				return false;
			myCommand = commandName();
			return getCommand(parser).equals(commandName());
		}

		protected abstract String commandName();
		protected abstract ParenAllExpression constructParenExpression(List<Expression> subExpressions);

		@Override
		public Expression parseExpression(Parser parser, HashMap<String, Expression> varMap) {
			if(!isKindOfExpression(parser, varMap))
				throw new ParserException("Attempt to parse invalid string as " + commandName() + " paren expression");

			List<Expression> subexpressions = new ArrayList<Expression>();
			parser.advanceCurrentPosition(commandName().length()+1);

			while(parser.notAtEndOfString()) {
				if(parser.stringAtCurrentPosition().equals(")")) {
					parser.advanceCurrentPosition(1);
					break;
				}
				subexpressions.add(parser.parseExpression(varMap));
			}
			return constructParenExpression(subexpressions);
		}       
	}
}
