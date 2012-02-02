package model.expression;

import java.util.List;

import model.RGBColor;
import model.util.PerlinNoise;

public class PerlinColorExpression extends ParenExpression {

    protected PerlinColorExpression(List<Expression> subexpressions) {
        super(subexpressions);
    }

    @Override
    public RGBColor evaluate() {
        List<RGBColor> results = evaluateSubexpressions();
        return colorNoise(results.get(0), results.get(1));
    }
    
    public static RGBColor colorNoise (RGBColor left, RGBColor right)
    {
        return new RGBColor(
                PerlinNoise.noise(left.getRed() + 0.3, right.getRed() + 0.3, 0),
                PerlinNoise.noise(left.getGreen() - 0.8, right.getGreen() - 0.8, 0),
                PerlinNoise.noise(left.getBlue() + 0.1, right.getBlue() + 0.1, 0));
    }
    
    public static class Factory extends ParenExpression.Factory
    {

        @Override
        protected String commandName() {
            return "perlinColor";
        }

        @Override
        protected int numberOfParameters() {
            return 2;
        }

        @Override
        protected ParenExpression constructParenExpression(
                List<Expression> subExpressions) {
            return new PerlinColorExpression(subExpressions);
        }
    }
}
