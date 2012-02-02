package model.expression;

import java.util.List;

import model.RGBColor;

public class LogExpression extends ParenExpression {

    protected LogExpression(List<Expression> subexpressions) {
        super(subexpressions);
    }

    @Override
    public RGBColor evaluate() {
        List<RGBColor> results = evaluateSubexpressions();
        return logarithim(results.get(0));
    }
    
    public static RGBColor logarithim (RGBColor arg)
	{
		return new RGBColor(Math.log(arg.getRed()), 
				Math.log(arg.getGreen()),
				Math.log(arg.getBlue()));
		}
    
    public static class Factory extends ParenExpression.Factory
    {

        @Override
        protected String commandName() {
            return "log";
        }

        @Override
        protected int numberOfParameters() {
            return 1;
        }

        @Override
        protected ParenExpression constructParenExpression(
                List<Expression> subExpressions) {
            return new LogExpression(subExpressions);
        }
    }
}
