package model.expression;

import java.util.HashMap;
import java.util.List;

import model.RGBColor;
import model.expression.Expression;

public class ProductExpression extends ParenAllExpression {

	protected ProductExpression(List<Expression> subexpressions) {
		super(subexpressions);
	}

	@Override
    public RGBColor evaluate (HashMap<String, Expression> varMap, double evalX, double evalY, double myCurrentTime) {
		List<RGBColor> results = evaluateSubexpressions(varMap, evalX, evalY, myCurrentTime);
		RGBColor mult = new RGBColor(1);
		for(int i=0; i<results.size(); i++) {
			mult = mul(mult, results.get(i));
		}
		return mult;
	}

	public static RGBColor mul (RGBColor left, RGBColor right)
	{
    	return new RGBColor(left.getRed() * right.getRed(), 
				left.getGreen() * right.getGreen(),
				left.getBlue() * right.getBlue());
	}

	public static class Factory extends ParenAllExpression.Factory
	{

		@Override
		protected String commandName() {
			return "product";
		}

		@Override
		protected ParenAllExpression constructParenExpression(
				List<Expression> subExpressions) {
			return new ProductExpression(subExpressions);
		}
	}
}
