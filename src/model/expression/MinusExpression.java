package model.expression;

import java.util.HashMap;
import java.util.List;

import model.RGBColor;
import model.expression.Expression;
import model.expression.ParenExpression;

public class MinusExpression extends ParenExpression {

    protected MinusExpression(List<Expression> subexpressions) {
        super(subexpressions);
    }

    @Override
    public RGBColor evaluate (HashMap<String, Expression> varMap, double evalX, double evalY, double myCurrentTime) {
		List<RGBColor> results = evaluateSubexpressions(varMap, evalX, evalY, myCurrentTime);
        return subtract(results.get(0), results.get(1));
    }
    
    public static RGBColor subtract (RGBColor left, RGBColor right)
	{
		return new RGBColor(left.getRed() - right.getRed(), 
				left.getGreen() - right.getGreen(),
				left.getBlue() - right.getBlue());
	}
    
    public static class Factory extends ParenExpression.Factory
    {

        @Override
        protected String commandName() {
            return "minus";
        }

        protected String altName() {
			return "-";
		}
        
        @Override
        protected int numberOfParameters() {
            return 2;
        }

        @Override
        protected ParenExpression constructParenExpression(
                List<Expression> subExpressions) {
            return new MinusExpression(subExpressions);
        }
        
    }

}
