package model.expression;

import java.util.List;

import model.RGBColor;

public class LetExpression extends ParenExpression {

    protected LetExpression(List<Expression> subexpressions) {
        super(subexpressions);
    }

    @Override
    public RGBColor evaluate() {    	
        List<RGBColor> results = evaluateSubexpressions();
    	varColor = results.get(1);
        return results.get(2);
    }
    
    public static class Factory extends ParenExpression.Factory
    {

        @Override
        protected String commandName() {
            return "let";
        }

        @Override
        protected int numberOfParameters() {
            return 2;
        }

        @Override
        protected ParenExpression constructParenExpression(
                List<Expression> subExpressions) {
            return new LetExpression(subExpressions);
        }
    }
}
