package model.expression;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Parser;
import model.ParserException;
import model.RGBColor;

public class VarExpression extends Expression{

	private String tomatch;
	protected VarExpression(String tomatch) {
		this.tomatch = tomatch;
	};

	@Override
    public RGBColor evaluate (HashMap<String, Expression> varMap, double evalX, double evalY, double myCurrentTime) {
		for(String key:varMap.keySet()) {
			if(tomatch.equals(key)) {
				Expression varRGB = varMap.get(key);
				return varRGB.evaluate(varMap, evalX, evalY, myCurrentTime);
			}
		}
		throw new ParserException("Undefined Variable");
	}

	public static class Factory extends Expression.Factory {

		private static final Pattern VAR_REGIX =
				Pattern.compile("[a-zA-Z]");

		public boolean isKindOfExpression(Parser parser, HashMap<String, Expression> varMap) {
			Matcher varMatcher = VAR_REGIX.matcher(parser.stringAtCurrentPosition());
			varMatcher.find();
			if(regexMatches(VAR_REGIX, parser)) {
				String[] input = parser.stringAtCurrentPosition().split(" ");
				String match = input[0];

				while(match.charAt(match.length()-1)==')') {
					match = match.substring(0, match.length()-1);
				}
				
				for(String key:varMap.keySet()) {
					if(match.equals(key)) {
						return true;
					}
				}
			}
			return false;
		}

		@Override
		public Expression parseExpression(Parser parser, HashMap<String, Expression> varMap) {
			String string = "";

			while(parser.currentCharacter()!=')' && parser.currentCharacter()!=' ') {
				string = string +parser.currentCharacter();
				parser.advanceCurrentPosition(1);
			}

			return new VarExpression(string);
		}
	}
}
