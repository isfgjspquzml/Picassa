package model.expression;

import java.util.List;

import model.RGBColor;

public class ModExpression extends ParenExpression {

    protected ModExpression(List<Expression> subexpressions) {
        super(subexpressions);
    }

    @Override
    public RGBColor evaluate() {
        List<RGBColor> results = evaluateSubexpressions();
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
