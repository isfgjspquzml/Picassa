package model.expression;

import java.util.HashMap;
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
    public abstract RGBColor evaluate (HashMap<String, Expression> map, double evalX, double evalY, double myCurrentTime);
    public static abstract class Factory
    {
        public abstract boolean isKindOfExpression(Parser parser, HashMap<String, Expression> varMap);
		public abstract Expression parseExpression(Parser parser, HashMap<String, Expression> varMap);
        
        protected boolean regexMatches(Pattern regex, Parser parser) {
            Matcher expMatcher =
                regex.matcher(parser.stringAtCurrentPosition());
            return expMatcher.lookingAt();
        }
    }
}
