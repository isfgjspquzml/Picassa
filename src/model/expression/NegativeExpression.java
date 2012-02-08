package model.expression;

import java.util.HashMap;
import java.util.List;

import model.RGBColor;
import model.expression.Expression;
import model.expression.ParenExpression;

public class NegativeExpression extends ParenExpression{

    protected NegativeExpression(List<Expression> subexpressions) {
        super(subexpressions);
    }

    @Override
	public RGBColor evaluate(HashMap<String, Expression> varMap, double evalX, double evalY, double myCurrentTime) {
		List<RGBColor> results = evaluateSubexpressions(varMap, evalX, evalY, myCurrentTime);
        return neg(results.get(0));
    }
    
    public RGBColor neg(RGBColor arg) {
		return new RGBColor(-arg.getRed(),
				-arg.getGreen(),
				-arg.getBlue());
	}
    
    public static class Factory extends ParenExpression.Factory
    {

        @Override
        protected String commandName() {
            return "neg";
        }
        
        protected String altName() {
			return "!";
		}

        @Override
        protected int numberOfParameters() {
            return 1;
        }

        @Override
        protected ParenExpression constructParenExpression(
                List<Expression> subExpressions) {
            return new NegativeExpression(subExpressions);
        }
    }
}
