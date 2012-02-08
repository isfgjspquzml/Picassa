package model.expression;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Parser;
import model.RGBColor;

public class NumberExpression extends Expression {

    private RGBColor color;
    
    public NumberExpression(RGBColor gray) {
        this.color = gray;
    }

    @Override
    public RGBColor evaluate (HashMap<String, Expression> varMap, double evalX, double evalY, double myCurrentTime) {
        return color;
    }

    public static class Factory extends Expression.Factory
    {
        // double is made up of an optional negative sign, followed by a sequence 
        // of one or more digits, optionally followed by a period, then possibly 
        // another sequence of digits
        private static final Pattern DOUBLE_REGEX =
            Pattern.compile("(\\-?[0-9]+(\\.[0-9]+)?)|(\\.[0-9]+)");

        @Override
		public boolean isKindOfExpression(Parser parser, HashMap<String, Expression> varMap) {
            return regexMatches(DOUBLE_REGEX, parser);
        }

        @Override
		public Expression parseExpression(Parser parser, HashMap<String, Expression> varMap) {
            String input = parser.stringAtCurrentPosition();
            Matcher doubleMatcher = DOUBLE_REGEX.matcher(input);
            doubleMatcher.find(0);
            String numberMatch =
                input.substring(doubleMatcher.start(), doubleMatcher.end());
            parser.advanceCurrentPosition(numberMatch.length());
            // this represents the color gray of the given intensity
            double value = Double.parseDouble(numberMatch);
            RGBColor gray = new RGBColor(value);
            return new NumberExpression(gray);
        }
    }
}
