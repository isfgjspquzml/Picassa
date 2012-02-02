package model.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Parser;
import model.ParserException;
import model.RGBColor;

public class VarNameExpression extends Expression {

	private RGBColor color;

	public VarNameExpression(RGBColor gray) {
		this.color = gray;
	}

	@Override
	public RGBColor evaluate() {
		return color;
	}

	public static class Factory extends Expression.Factory
	{
		private static final Pattern VARNAME_BEGIN_REGEX =
				Pattern.compile("[a-zA-Z]");

		@Override
		public boolean isKindOfExpression(Parser parser) {
			return regexMatches(VARNAME_BEGIN_REGEX, parser);
		}

		public Expression parseExpression(Parser parser) {
			String input = parser.stringAtCurrentPosition();
			Matcher varMatcher = VARNAME_BEGIN_REGEX.matcher(input);

			String varMatch =
					input.substring(varMatcher.start(), varMatcher.end());
			parser.advanceCurrentPosition(varMatch.length());
			// this represents the color gray of the given intensity
			return new VarNameExpression(varColor);
		}
	}
}
