package model.expression;

import java.util.HashMap;
import java.util.List;

import model.RGBColor;
import model.expression.Expression;
import model.expression.ParenExpression;

public class TanExpression extends ParenExpression {

	protected TanExpression(List<Expression> subexpressions) {
		super(subexpressions);
	}

	@Override
	public RGBColor evaluate (HashMap<String, Expression> varMap, double evalX, double evalY, double myCurrentTime) {
		List<RGBColor> results = evaluateSubexpressions(varMap, evalX, evalY, myCurrentTime);
		return tangent(results.get(0));
	}

	public static RGBColor tangent (RGBColor arg)
	{
		return new RGBColor(Math.tan(arg.getRed()), 
				Math.tan(arg.getGreen()),
				Math.tan(arg.getBlue()));	}

	public static class Factory extends ParenExpression.Factory
	{

		@Override
		protected String commandName() {
			return "tan";
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
			return new TanExpression(subExpressions);
		}
	}
}
