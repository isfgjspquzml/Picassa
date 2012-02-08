package model.expression;

import java.util.HashMap;

import model.RGBColor;
import model.expression.Expression;

public class YExpression extends VariableExpression {

	public YExpression() {};

	@Override
	public RGBColor evaluate (HashMap<String, Expression> varMap, double evalX, double evalY, double myCurrentTime) {
		return new RGBColor(evalY);
	}

	public static class Factory extends VariableExpression.Factory
	{

		@Override
		protected String commandName() {
			return "y";
		}

		@Override
		protected VariableExpression constructParenExpression() {
			return new YExpression();
		}

	}
}