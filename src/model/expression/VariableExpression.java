package model.expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Parser;
import model.RGBColor;

public abstract class VariableExpression extends Expression {
    
    public VariableExpression() {}

    @Override
    public abstract RGBColor evaluate();

    public static class Factory extends Expression.Factory
    {
        private static final Pattern XY_REGIX =
            Pattern.compile("x|y");

        @Override
        public boolean isKindOfExpression(Parser parser) {
            return regexMatches(XY_REGIX, parser);
        }

        @Override
        public Expression parseExpression(Parser parser) {
            String input = parser.stringAtCurrentPosition();
            Matcher XYMatcher = XY_REGIX.matcher(input);
            XYMatcher.find(0);
            String XYMatch =
                input.substring(XYMatcher.start(), XYMatcher.end());
            parser.advanceCurrentPosition(XYMatch.length());
            return new XYExpression(XYMatch);
        }
        
    }
    
}
