package model.expression;

import java.util.List;

import model.RGBColor;
import model.util.PerlinNoise;

public class PerlinBWExpression extends ParenExpression {

    protected PerlinBWExpression(List<Expression> subexpressions) {
        super(subexpressions);
    }

    @Override
    public RGBColor evaluate() {
        List<RGBColor> results = evaluateSubexpressions();
        return greyNoise(results.get(0), results.get(1));
    }
    
    public static RGBColor greyNoise (RGBColor left, RGBColor right)
    {
       return new RGBColor(
               PerlinNoise.noise(left.getRed() + right.getRed(),
                                 left.getGreen() + right.getGreen(),
                                 left.getBlue() + right.getBlue()));
    }
    
    public static class Factory extends ParenExpression.Factory
    {

        @Override
        protected String commandName() {
            return "perlinBW";
        }

        @Override
        protected int numberOfParameters() {
            return 2;
        }

        @Override
        protected ParenExpression constructParenExpression(
                List<Expression> subExpressions) {
            return new PerlinBWExpression(subExpressions);
        }
    }
}
