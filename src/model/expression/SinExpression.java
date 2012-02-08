package model.expression;

import java.util.HashMap;
import java.util.List;

import model.RGBColor;
import model.expression.Expression;
import model.expression.ParenExpression;

public class SinExpression extends ParenExpression {

	protected SinExpression(List<Expression> subexpressions) {
		super(subexpressions);
	}

	@Override
	public RGBColor evaluate (HashMap<String, Expression> varMap, double evalX, double evalY, double myCurrentTime) {
		List<RGBColor> results = evaluateSubexpressions(varMap, evalX, evalY, myCurrentTime);
		return sine(results.get(0));
	}

	public static RGBColor sine (RGBColor arg)
	{
		return new RGBColor(Math.sin(arg.getRed()), 
				Math.sin(arg.getGreen()),
				Math.sin(arg.getBlue()));	}

	public static class Factory extends ParenExpression.Factory
	{

		@Override
		protected String commandName() {
			return "sin";
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
			return new SinExpression(subExpressions);
		}
	}
}
