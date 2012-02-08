package model.expression;

import java.util.HashMap;

import model.RGBColor;
import model.expression.Expression;

public class TExpression extends VariableExpression {

	public TExpression() {};

	@Override
	public RGBColor evaluate (HashMap<String, Expression> varMap, double evalX, double evalY, double myCurrentTime) {
		return new RGBColor(myCurrentTime);
	}

	public static class Factory extends VariableExpression.Factory
	{

		@Override
		protected String commandName() {
			return "t";
		}

		@Override
		protected VariableExpression constructParenExpression() {
			return new TExpression();
		}

	}
}