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

	protected List<RGBColor> evaluateSubexpressions()
	{
		List<RGBColor> result = new ArrayList<RGBColor>(subexpressions.size());
		for(Expression exp : subexpressions) {
			result.add(exp.evaluate());
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
		public boolean isKindOfExpression(Parser parser) {
			if(!regexMatches(EXPRESSION_BEGIN_REGEX,parser))
				return false;
			myCommand = commandName();
			return getCommand(parser).equals(commandName());
		}

		protected abstract String commandName();
		protected abstract int numberOfParameters();
		protected abstract ParenExpression constructParenExpression(List<Expression> subExpressions);

		@Override
		public Expression parseExpression(Parser parser) {
			if(!isKindOfExpression(parser))
				throw new ParserException("Attempt to parse invalid string as " + commandName() + " paren expression");
			//move past left paren and command name
			parser.advanceCurrentPosition(commandName().length() + 1);

			if(myCommand.equals("let")) {
				parser.advanceCurrentPosition(1);
				varname = "";

				while(parser.currentCharacter()!=' ') {
					varname = varname +	parser.currentCharacter();
					parser.advanceCurrentPosition(1);
				}	
				parser.advanceCurrentPosition(varname.length() + 1);
			}

			List<Expression> subexpressions = new ArrayList<Expression>();
			for(int i = 0; i < numberOfParameters(); i++) {
				subexpressions.add(parser.parseExpression());
			}

			parser.skipWhiteSpace();
			if (parser.currentCharacter() == ')')
			{
				parser.advanceCurrentPosition(1);
				return constructParenExpression(subexpressions);
			}
			else
			{
				throw new ParserException("Expected close paren, instead found " +
						parser.stringAtCurrentPosition());
			}
		}       
	}
}
