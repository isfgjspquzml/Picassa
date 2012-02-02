package model.expression;

import java.util.List;

import model.RGBColor;

public class NegativeExpression extends ParenExpression{

    protected NegativeExpression(List<Expression> subexpressions) {
        super(subexpressions);
    }

    @Override
    public RGBColor evaluate() {
        List<RGBColor> results = evaluateSubexpressions();
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
