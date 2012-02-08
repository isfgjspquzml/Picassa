package model.expression;

import java.util.*;
import java.util.regex.*;

import model.*;

public abstract class ParenExpression extends Expression {

	private static String myCommand;
	private List<Expression> subexpressions;

	protected ParenExpression(List<Expression> subexpressions)
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
			String[] symbol = parser.stringAtCurrentPosition().split(" ");
			String tomatch = symbol[0];
			myCommand = altName();

			if(tomatch.substring(1).equals(altName())) {
				return true;
			}
			
			if(!regexMatches(EXPRESSION_BEGIN_REGEX,parser)) {
				return false;}
			myCommand = commandName();
			return getCommand(parser).equals(commandName());
		}

		protected abstract String altName();
		protected abstract String commandName();
		protected abstract int numberOfParameters();
		protected abstract ParenExpression constructParenExpression(List<Expression> subExpressions);

		@Override
		public Expression parseExpression(Parser parser, HashMap<String, Expression> varMap) {
			if(!isKindOfExpression(parser, varMap))
				throw new ParserException("Attempt to parse invalid string as " + commandName() + " paren expression");

			List<Expression> subexpressions = new ArrayList<Expression>();

			parser.advanceCurrentPosition(myCommand.length()+1);

			if(myCommand.equals("let")) {
				String[] input = parser.stringAtCurrentPosition().substring(1).split(" ");
				String varname = input[0];

				parser.advanceCurrentPosition(varname.length() + 1);

				Expression varequals = parser.parseExpression(varMap);
				varMap.put(varname, varequals);

				subexpressions.add(parser.parseExpression(varMap));
			}

			for(int i = 0; i < numberOfParameters(); i++) {
				subexpressions.add(parser.parseExpression(varMap));
			}

			if(parser.currentCharacter()==')') {
				parser.advanceCurrentPosition(1);
			}

			return constructParenExpression(subexpressions);
		}       
	}
}
