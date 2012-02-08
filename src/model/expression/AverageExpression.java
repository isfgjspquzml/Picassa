package model.expression;

import java.util.HashMap;
import java.util.List;

import model.RGBColor;
import model.expression.Expression;

public class AverageExpression extends ParenAllExpression {

	protected AverageExpression(List<Expression> subexpressions) {
		super(subexpressions);
	}

	@Override
    public RGBColor evaluate (HashMap<String, Expression> varMap, double evalX, double evalY, double myCurrentTime) {
		List<RGBColor> results = evaluateSubexpressions(varMap, evalX, evalY, myCurrentTime);
		RGBColor sum = new RGBColor(0);
		int count = 0;

		for(int i=0; i<results.size(); i++) {
			count++;
			sum = add(sum, results.get(i));
		}

		RGBColor divider = new RGBColor(count);

		return div(sum, divider);
	}

	public static RGBColor add (RGBColor left, RGBColor right)
	{
		return new RGBColor(left.getRed() + right.getRed(), 
				left.getGreen() + right.getGreen(),
				left.getBlue() + right.getBlue());
	}

	public static RGBColor div (RGBColor left, RGBColor right)
	{
		return new RGBColor(left.getRed() / right.getRed(), 
				left.getGreen() / right.getGreen(),
				left.getBlue() / right.getBlue());
	}

	public static class Factory extends ParenAllExpression.Factory
	{

		@Override
		protected String commandName() {
			return "average";
		}

		String altName() {
			return "";
		}
		
		@Override
		protected ParenAllExpression constructParenExpression(
				List<Expression> subExpressions) {
			return new AverageExpression(subExpressions);
		}
	}
}
