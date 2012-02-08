package model.expression;

import java.util.HashMap;
import java.util.List;

import model.RGBColor;
import model.expression.Expression;
import model.expression.ParenExpression;

public class ModExpression extends ParenExpression {

    protected ModExpression(List<Expression> subexpressions) {
        super(subexpressions);
    }

    @Override
    public RGBColor evaluate (HashMap<String, Expression> varMap, double evalX, double evalY, double myCurrentTime) {
		List<RGBColor> results = evaluateSubexpressions(varMap, evalX, evalY, myCurrentTime);
        return mod(results.get(0), results.get(1));
    }
    
    public static RGBColor mod (RGBColor left, RGBColor right)
	{
    	return new RGBColor(left.getRed() % right.getRed(), 
				left.getGreen() % right.getGreen(),
				left.getBlue() % right.getBlue());
	}
    
    public static class Factory extends ParenExpression.Factory
    {

        @Override
        protected String commandName() {
            return "mod";
        }
        
        protected String altName() {
			return "%";
		}
        
        @Override
        protected int numberOfParameters() {
            return 2;
        }

        @Override
        protected ParenExpression constructParenExpression(
                List<Expression> subExpressions) {
            return new ModExpression(subExpressions);
        }
        
    }

}
