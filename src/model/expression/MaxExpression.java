package model.expression;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import model.RGBColor;
import model.expression.Expression;

public class MaxExpression extends ParenAllExpression {

	protected MaxExpression(List<Expression> subexpressions) {
		super(subexpressions);
	}

	@Override
    public RGBColor evaluate (HashMap<String, Expression> varMap, double evalX, double evalY, double myCurrentTime) {
		List<RGBColor> results = evaluateSubexpressions(varMap, evalX, evalY, myCurrentTime);
		Collections.sort(results);
		return results.get(results.size()-1) ;
	}

	public static class Factory extends ParenAllExpression.Factory
	{

		@Override
		protected String commandName() {
			return "max";
		}

		@Override
		protected ParenAllExpression constructParenExpression(
				List<Expression> subExpressions) {
			return new MaxExpression(subExpressions);
		}
	}
}
