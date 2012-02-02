package model.expression;

import java.util.List;

import model.RGBColor;

public class DivideExpression extends ParenExpression {

    protected DivideExpression(List<Expression> subexpressions) {
        super(subexpressions);
    }

    @Override
    public RGBColor evaluate() {
        List<RGBColor> results = evaluateSubexpressions();
        return div(results.get(0), results.get(1));
    }
    
    public static RGBColor div (RGBColor left, RGBColor right)
	{
    	return new RGBColor(left.getRed() / right.getRed(), 
				left.getGreen() / right.getGreen(),
				left.getBlue() / right.getBlue());
	}
    
    public static class Factory extends ParenExpression.Factory
    {

        @Override
        protected String commandName() {
            return "div";
        }

        @Override
        protected int numberOfParameters() {
            return 2;
        }

        @Override
        protected ParenExpression constructParenExpression(
                List<Expression> subExpressions) {
            return new DivideExpression(subExpressions);
        }
        
    }

}
