package model.expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.*;

/**
 * An Expression represents a mathematical expression as a tree.
 * 
 * In this format, the internal nodes represent mathematical 
 * functions and the leaves represent constant values.
 *
 * @author former student solution
 * @author Robert C. Duvall (added comments, some code)
 */
public abstract class Expression
{
	protected static String varname;
	protected static RGBColor varColor;

    public abstract RGBColor evaluate ();
    public static abstract class Factory
    {
        public abstract boolean isKindOfExpression(Parser parser);
        public abstract Expression parseExpression(Parser parser);
        
        protected boolean regexMatches(Pattern regex, Parser parser) {
            Matcher expMatcher =
                regex.matcher(parser.stringAtCurrentPosition());
            return expMatcher.lookingAt();
        }
    }

}
