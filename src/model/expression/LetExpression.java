package model.expression;

import java.util.HashMap;
import java.util.List;

import model.RGBColor;

public class LetExpression extends ParenExpression {

	protected LetExpression(List<Expression> subexpressions) {
		super(subexpressions);
	}

	@Override
	public RGBColor evaluate (HashMap<String, Expression> varMap, double evalX, double evalY, double myCurrentTime) {
		HashMap<String, Expression> newvarMap = new HashMap<String, Expression>();
		newvarMap.putAll(varMap);

		List<RGBColor> results = evaluateSubexpressions(varMap, evalX, evalY, myCurrentTime);
		return results.get(0);
	}

	public static class Factory extends ParenExpression.Factory
	{

		@Override
		protected String commandName() {
			return "let";
		}

		protected String altName() {
			return "";
		}

		@Override
		protected int numberOfParameters() {
			return 0;
		}

		@Override
		protected ParenExpression constructParenExpression(
				List<Expression> subExpressions) {
			return new LetExpression(subExpressions);
		}
	}
}
