package model.expression;

import java.util.HashMap;
import java.util.List;

import model.RGBColor;
import model.expression.Expression;
import model.expression.ParenExpression;

public class ColorExpression extends ParenExpression {

    protected ColorExpression(List<Expression> subexpressions) {
        super(subexpressions);
    }

    @Override
    public RGBColor evaluate (HashMap<String, Expression> varMap, double evalX, double evalY, double myCurrentTime) {
		List<RGBColor> results = evaluateSubexpressions(varMap, evalX, evalY, myCurrentTime);
        return new RGBColor(results.get(0).getRed(),
                results.get(1).getRed(),
                results.get(2).getRed());
    }
    
    public static class Factory extends ParenExpression.Factory
    {

        @Override
        protected String commandName() {
            return "color";
        }

        protected String altName() {
			return "";
		}
        
        @Override
        protected int numberOfParameters() {
            return 3;
        }

        @Override
        protected ParenExpression constructParenExpression(
                List<Expression> subExpressions) {
            return new ColorExpression(subExpressions);
        }
        
    }

}
