package model.expression;

import java.util.List;

import model.RGBColor;

public class yCrCbtoRGBExpression extends ParenExpression {

    protected yCrCbtoRGBExpression(List<Expression> subexpressions) {
        super(subexpressions);
    }

    @Override
    public RGBColor evaluate() {
        List<RGBColor> results = evaluateSubexpressions();
        return ycrcb2rgb(results.get(0));
    }
    
    public static RGBColor ycrcb2rgb (RGBColor c)
    {
        return new RGBColor(
                    c.getRed() + c.getBlue() *  1.4022,
                    c.getRed() + c.getGreen() * -0.3456 + c.getBlue() * -0.7145,
                    c.getRed() + c.getGreen() *  1.7710);
    }
    
    public static class Factory extends ParenExpression.Factory
    {

        @Override
        protected String commandName() {
            return "yCrCbtoRGB";
        }

        @Override
        protected int numberOfParameters() {
            return 1;
        }

        @Override
        protected ParenExpression constructParenExpression(
                List<Expression> subExpressions) {
            return new yCrCbtoRGBExpression(subExpressions);
        }
    }
}
