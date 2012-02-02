package model.expression;

import java.util.List;

import model.RGBColor;

public class SinExpression extends ParenExpression {

    protected SinExpression(List<Expression> subexpressions) {
        super(subexpressions);
    }

    @Override
    public RGBColor evaluate() {
        List<RGBColor> results = evaluateSubexpressions();
        return sine(results.get(0));
    }
    
    public static RGBColor sine (RGBColor arg)
	{
		return new RGBColor(Math.sin(arg.getRed()), 
				Math.sin(arg.getGreen()),
				Math.sin(arg.getBlue()));	}
    
    public static class Factory extends ParenExpression.Factory
    {

        @Override
        protected String commandName() {
            return "sin";
        }

        @Override
        protected int numberOfParameters() {
            return 1;
        }

        @Override
        protected ParenExpression constructParenExpression(
                List<Expression> subExpressions) {
            return new SinExpression(subExpressions);
        }
    }
}
