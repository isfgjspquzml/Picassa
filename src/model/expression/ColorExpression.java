package model.expression;

import java.util.List;

import model.RGBColor;

public class ColorExpression extends ParenExpression {

    protected ColorExpression(List<Expression> subexpressions) {
        super(subexpressions);
    }

    @Override
    public RGBColor evaluate() {
        List<RGBColor> results = evaluateSubexpressions();
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
