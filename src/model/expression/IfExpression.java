package model.expression;

import java.util.HashMap;
import java.util.List;

import model.RGBColor;
import model.expression.Expression;
import model.expression.ParenExpression;

public class IfExpression extends ParenExpression {

	protected IfExpression(List<Expression> subexpressions) {
		super(subexpressions);
	}

	@Override
	public RGBColor evaluate (HashMap<String, Expression> varMap, double evalX, double evalY, double myCurrentTime) {
		List<RGBColor> results = evaluateSubexpressions(varMap, evalX, evalY, myCurrentTime);
		RGBColor zero = new RGBColor(0);
		if(results.get(0).compareTo(zero)>0) {
			return results.get(1);
		}
		else {
			return results.get(2);
		}
	}

	public static class Factory extends ParenExpression.Factory
	{

		@Override
		protected String commandName() {
			return "if";
		}

		protected String altName() {
			return "";
		}

		@Override
		protected int numberOfParameters() {
			return 3;
		}

		@Override
		protected ParenExpression constructParenExpression(
				List<Expression> subExpressions) {
			return new IfExpression(subExpressions);
		}
	}
}
