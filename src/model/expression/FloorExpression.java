package model.expression;

import java.util.HashMap;
import java.util.List;

import model.RGBColor;
import model.expression.Expression;
import model.expression.ParenExpression;

public class FloorExpression extends ParenExpression {

	protected FloorExpression(List<Expression> subexpressions) {
		super(subexpressions);
	}

	@Override
    public RGBColor evaluate (HashMap<String, Expression> varMap, double evalX, double evalY, double myCurrentTime) {
		List<RGBColor> results = evaluateSubexpressions(varMap, evalX, evalY, myCurrentTime);
		return floor(results.get(0));
	}

	public static RGBColor floor (RGBColor arg)
	{
		return new RGBColor(Math.floor(arg.getRed()), 
				Math.floor(arg.getGreen()),
				Math.floor(arg.getBlue()));
	}

	public static class Factory extends ParenExpression.Factory
	{

		@Override
		protected String commandName() {
			return "floor";
		}
		
        protected String altName() {
        	return "";
        }


		@Override
		protected int numberOfParameters() {
			return 1;
		}

		@Override
		protected ParenExpression constructParenExpression(
				List<Expression> subExpressions) {
			return new FloorExpression(subExpressions);
		}
	}
}
