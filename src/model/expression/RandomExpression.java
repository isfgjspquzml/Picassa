package model.expression;

import java.util.HashMap;
import java.util.List;

import model.Model;
import model.RGBColor;
import model.expression.Expression;
import model.expression.ParenExpression;

public class RandomExpression extends ParenExpression {

	private static double randomNum1;
	private static double randomNum2;
	private static double randomNum3;
	
    protected RandomExpression(List<Expression> subexpressions) {
        super(subexpressions);
    }

    @Override
    public RGBColor evaluate (HashMap<String, Expression> varMap, double evalX, double evalY, double myCurrentTime) {
        return random();
    }
    
    public static RGBColor random ()
	{
		return new RGBColor(randomNum1, randomNum2, randomNum3);
	}
    
    public static class Factory extends ParenExpression.Factory
    {

        @Override
        protected String commandName() {
       	 randomNum1 = Model.DOMAIN_MIN + (Math.random()*Model.DOMAIN_MAX);
    	 randomNum2 = Model.DOMAIN_MIN + (Math.random()*Model.DOMAIN_MAX);
    	 randomNum3 = Model.DOMAIN_MIN + (Math.random()*Model.DOMAIN_MAX);
            return "random";
        }
        
        protected String altName() {
            return "";
        }

        @Override
        protected int numberOfParameters() {
            return 0;
        }

        @Override
        protected ParenExpression constructParenExpression(
                List<Expression> subExpressions) {
            return new RandomExpression(subExpressions);
        }
    }
}
