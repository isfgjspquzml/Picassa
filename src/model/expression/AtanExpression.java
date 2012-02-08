package model.expression;

import java.util.HashMap;
import java.util.List;

import model.RGBColor;
import model.expression.Expression;
import model.expression.ParenExpression;

public class AtanExpression extends ParenExpression {

    protected AtanExpression(List<Expression> subexpressions) {
        super(subexpressions);
    }

    @Override
    public RGBColor evaluate (HashMap<String, Expression> varMap, double evalX, double evalY, double myCurrentTime) {
		List<RGBColor> results = evaluateSubexpressions(varMap, evalX, evalY, myCurrentTime);
        return arctan(results.get(0));
    }
    
    public static RGBColor arctan (RGBColor arg)
	{
		return new RGBColor(Math.atan(arg.getRed()), 
				Math.atan(arg.getGreen()),
				Math.atan(arg.getBlue()));
		}
    
    public static class Factory extends ParenExpression.Factory
    {

        @Override
        protected String commandName() {
            return "atan";
        }

		protected String altName() {
			return "";
		}
        
        @Override
        protected int numberOfParameters() {
            return 1;
        }

        @Override
        protected ParenExpression constructParenExpression(
                List<Expression> subExpressions) {
            return new AtanExpression(subExpressions);
        }
    }
}
