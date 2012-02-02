package model.expression;

import java.util.List;

import model.RGBColor;

public class CeilExpression extends ParenExpression {

	protected CeilExpression(List<Expression> subexpressions) {
		super(subexpressions);
	}

	@Override
	public RGBColor evaluate() {
		List<RGBColor> results = evaluateSubexpressions();
		return ceil(results.get(0));
	}

	public static RGBColor ceil (RGBColor arg)
	{
		return new RGBColor(Math.ceil(arg.getRed()), 
				Math.ceil(arg.getGreen()),
				Math.ceil(arg.getBlue()));
	}

	public static class Factory extends ParenExpression.Factory
	{

		@Override
		protected String commandName() {
			return "ceil";
		}

		@Override
		protected int numberOfParameters() {
			return 1;
		}

		@Override
		protected ParenExpression constructParenExpression(
				List<Expression> subExpressions) {
			return new CeilExpression(subExpressions);
		}
	}
}
