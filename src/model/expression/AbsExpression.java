package model.expression;

import java.util.List;

import model.RGBColor;

public class AbsExpression extends ParenExpression {

	protected AbsExpression(List<Expression> subexpressions) {
		super(subexpressions);
	}

	@Override
	public RGBColor evaluate() {
		List<RGBColor> results = evaluateSubexpressions();
		return abs(results.get(0));
	}

	public static RGBColor abs (RGBColor arg)
	{
		return new RGBColor(Math.abs(arg.getRed()), 
				Math.abs(arg.getGreen()),
				Math.abs(arg.getBlue()));
	}

	public static class Factory extends ParenExpression.Factory
	{

		@Override
		protected String commandName() {
			return "abs";
		}

		@Override
		protected int numberOfParameters() {
			return 1;
		}

		@Override
		protected ParenExpression constructParenExpression(
				List<Expression> subExpressions) {
			return new AbsExpression(subExpressions);
		}
	}
}
