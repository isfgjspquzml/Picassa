package model.expression;

import java.util.HashMap;

import model.RGBColor;
import model.expression.Expression;

public class XExpression extends VariableExpression {

	public XExpression() {};

	@Override
	public RGBColor evaluate (HashMap<String, Expression> varMap, double evalX, double evalY, double myCurrentTime) {
		return new RGBColor(evalX);
	}

	public static class Factory extends VariableExpression.Factory
	{

		@Override
		protected String commandName() {
			return "x";
		}

		@Override
		protected VariableExpression constructParenExpression() {
			return new XExpression();
		}

	}
}