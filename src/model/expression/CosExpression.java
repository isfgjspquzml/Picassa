package model.expression;

import java.util.HashMap;
import java.util.List;

import model.RGBColor;
import model.expression.Expression;
import model.expression.ParenExpression;

public class CosExpression extends ParenExpression {

    protected CosExpression(List<Expression> subexpressions) {
        super(subexpressions);
    }

    @Override
    public RGBColor evaluate (HashMap<String, Expression> varMap, double evalX, double evalY, double myCurrentTime) {
		List<RGBColor> results = evaluateSubexpressions(varMap, evalX, evalY, myCurrentTime);
        return cosine(results.get(0));
    }
    
    public static RGBColor cosine (RGBColor arg)
	{
		return new RGBColor(Math.cos(arg.getRed()), 
				Math.cos(arg.getGreen()),
				Math.cos(arg.getBlue()));	}
    
    public static class Factory extends ParenExpression.Factory
    {

        @Override
        protected String commandName() {
            return "cos";
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
            return new CosExpression(subExpressions);
        }
    }
}
