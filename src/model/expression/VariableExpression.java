package model.expression;

import java.util.*;
import java.util.regex.*;

import model.*;

public abstract class VariableExpression extends Expression {

	private static String myCommand;

	public abstract static class Factory extends Expression.Factory
	{
		private static final Pattern XYT_BEGIN_REGEX =
				Pattern.compile("x|y|t");

		protected String getCommand(Parser parser) {
			Matcher xytMatcher = XYT_BEGIN_REGEX.matcher(parser.stringAtCurrentPosition());
			xytMatcher.find();
			String xytmatch = 
					parser.stringAtCurrentPosition().substring(xytMatcher.start(), xytMatcher.end());
			return xytmatch;
		}

		@Override
		public boolean isKindOfExpression(Parser parser, HashMap<String, Expression> varMap) {
			if(!regexMatches(XYT_BEGIN_REGEX,parser)) {
				return false;}
			myCommand = commandName();
			return getCommand(parser).equals(commandName());
		}

		protected abstract String commandName();
		protected abstract VariableExpression constructParenExpression();

		@Override
		public Expression parseExpression(Parser parser, HashMap<String, Expression> varMap) {
			if(!isKindOfExpression(parser, varMap))
				throw new ParserException("Attempt to parse invalid string as " + commandName() + " paren expression");

			parser.advanceCurrentPosition(commandName().length());

			return constructParenExpression();
		}       
	}
}
