package model.expression;

import java.util.List;

import model.RGBColor;

public class TanExpression extends ParenExpression {

    protected TanExpression(List<Expression> subexpressions) {
        super(subexpressions);
    }

    @Override
    public RGBColor evaluate() {
        List<RGBColor> results = evaluateSubexpressions();
        return tangent(results.get(0));
    }
    
    public static RGBColor tangent (RGBColor arg)
	{
		return new RGBColor(Math.tan(arg.getRed()), 
				Math.tan(arg.getGreen()),
				Math.tan(arg.getBlue()));	}
    
    public static class Factory extends ParenExpression.Factory
    {

        @Override
        protected String commandName() {
            return "tan";
        }

        @Override
        protected int numberOfParameters() {
            return 1;
        }

        @Override
        protected ParenExpression constructParenExpression(
                List<Expression> subExpressions) {
            return new TanExpression(subExpressions);
        }
    }
}
