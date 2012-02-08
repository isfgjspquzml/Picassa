package model.expression;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import model.RGBColor;
import model.expression.Expression;

public class MinExpression extends ParenAllExpression {

	protected MinExpression(List<Expression> subexpressions) {
		super(subexpressions);
	}

	@Override
    public RGBColor evaluate (HashMap<String, Expression> varMap, double evalX, double evalY, double myCurrentTime) {
		List<RGBColor> results = evaluateSubexpressions(varMap, evalX, evalY, myCurrentTime);
		Collections.sort(results);
		return results.get(0) ;
	}

	public static class Factory extends ParenAllExpression.Factory
	{

		@Override
		protected String commandName() {
			return "min";
		}

		@Override
		protected ParenAllExpression constructParenExpression(
				List<Expression> subExpressions) {
			return new MinExpression(subExpressions);
		}
	}
}
