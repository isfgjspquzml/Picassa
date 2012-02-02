package model.expression;

import model.Model;
import model.RGBColor;

public class XYExpression extends VariableExpression{

	private String XYMatch;

	public XYExpression(String XYMatch) {
		this.XYMatch = XYMatch;
	}

	@Override
	public RGBColor evaluate() {
		if(XYMatch.equals("x")) {
			return new RGBColor(Model.evalX);
		}
		if(XYMatch.equals("y")) {
			return new RGBColor(Model.evalY);
		}
		return new RGBColor(0);
	}
}
