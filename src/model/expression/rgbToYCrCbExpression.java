package model.expression;

import java.util.HashMap;
import java.util.List;

import model.RGBColor;
import model.expression.Expression;
import model.expression.ParenExpression;

public class rgbToYCrCbExpression extends ParenExpression {

	protected rgbToYCrCbExpression(List<Expression> subexpressions) {
		super(subexpressions);
	}

	@Override
	public RGBColor evaluate (HashMap<String, Expression> varMap, double evalX, double evalY, double myCurrentTime) {
		List<RGBColor> results = evaluateSubexpressions(varMap, evalX, evalY, myCurrentTime);
		return rgb2ycrcb(results.get(0));
	}

	public static RGBColor rgb2ycrcb (RGBColor c)
	{
		return new RGBColor(
				c.getRed() *  0.2989 + c.getGreen() *  0.5866 + c.getBlue() *  0.1145,
				c.getRed() * -0.1687 + c.getGreen() * -0.3312 + c.getBlue() *  0.5,
				c.getRed() *  0.5000 + c.getGreen() * -0.4183 + c.getBlue() * -0.0816);
	}

	public static class Factory extends ParenExpression.Factory
	{

		@Override
		protected String commandName() {
			return "rgbToYCrCb";
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
			return new rgbToYCrCbExpression(subExpressions);
		}
	}
}
