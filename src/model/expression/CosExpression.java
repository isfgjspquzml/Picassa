package model.expression;

import java.util.List;

import model.RGBColor;

public class CosExpression extends ParenExpression {

    protected CosExpression(List<Expression> subexpressions) {
        super(subexpressions);
    }

    @Override
    public RGBColor evaluate() {
        List<RGBColor> results = evaluateSubexpressions();
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
