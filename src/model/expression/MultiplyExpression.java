package model.expression;

import java.util.List;

import model.RGBColor;

public class MultiplyExpression extends ParenExpression {

    protected MultiplyExpression(List<Expression> subexpressions) {
        super(subexpressions);
    }

    @Override
    public RGBColor evaluate() {
        List<RGBColor> results = evaluateSubexpressions();
        return mul(results.get(0), results.get(1));
    }
    
    public static RGBColor mul (RGBColor left, RGBColor right)
	{
    	return new RGBColor(left.getRed() * right.getRed(), 
				left.getGreen() * right.getGreen(),
				left.getBlue() * right.getBlue());
	}
    
    public static class Factory extends ParenExpression.Factory
    {

        @Override
        protected String commandName() {
            return "mul";
        }

        @Override
        protected int numberOfParameters() {
            return 2;
        }

        @Override
        protected ParenExpression constructParenExpression(
                List<Expression> subExpressions) {
            return new MultiplyExpression(subExpressions);
        }
        
    }

}
