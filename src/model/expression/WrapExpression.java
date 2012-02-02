package model.expression;

import java.util.List;

import model.RGBColor;

public class WrapExpression extends ParenExpression {

	protected WrapExpression(List<Expression> subexpressions) {
		super(subexpressions);
	}

	@Override
	public RGBColor evaluate() {
		List<RGBColor> results = evaluateSubexpressions();
		return wrapper(results.get(0));
	}

	public static RGBColor wrapper (RGBColor arg)
	{
		RGBColor wrapped = new RGBColor(arg.getRed(),
				arg.getGreen(), 
				arg.getBlue());
		wrapped.wrap();

		return wrapped;
	}

	public static class Factory extends ParenExpression.Factory
	{

		@Override
		protected String commandName() {
			return "wrap";
		}

		@Override
		protected int numberOfParameters() {
			return 1;
		}

		@Override
		protected ParenExpression constructParenExpression(
				List<Expression> subExpressions) {
			return new WrapExpression(subExpressions);
		}
	}
}