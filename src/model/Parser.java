package model;

import java.util.*;
import model.expression.*;

public class Parser
{

    public Parser()
    {
        constructExpressionTypes();
    }
    
    private int myCurrentPosition;
    private String myInput;
    private List<Expression.Factory> expressionTypes;
    
    private void constructExpressionTypes()
    {
        expressionTypes = new ArrayList<Expression.Factory>();
        expressionTypes.add(new NumberExpression.Factory());
        expressionTypes.add(new XExpression.Factory());
        expressionTypes.add(new YExpression.Factory());
        expressionTypes.add(new TExpression.Factory());
        expressionTypes.add(new VarExpression.Factory());
        expressionTypes.add(new LetExpression.Factory());
        expressionTypes.add(new VarExpression.Factory());
        expressionTypes.add(new PlusExpression.Factory());
        expressionTypes.add(new MinusExpression.Factory());
        expressionTypes.add(new ColorExpression.Factory());
        expressionTypes.add(new NegativeExpression.Factory());
        expressionTypes.add(new ExponentialExpression.Factory());
        expressionTypes.add(new DivideExpression.Factory());
        expressionTypes.add(new ModExpression.Factory());
        expressionTypes.add(new MultiplyExpression.Factory());
        expressionTypes.add(new RandomExpression.Factory());
        expressionTypes.add(new FloorExpression.Factory());
        expressionTypes.add(new CeilExpression.Factory());
        expressionTypes.add(new AbsExpression.Factory());
        expressionTypes.add(new ClampExpression.Factory());
        expressionTypes.add(new WrapExpression.Factory());
        expressionTypes.add(new SinExpression.Factory());
        expressionTypes.add(new CosExpression.Factory());
        expressionTypes.add(new TanExpression.Factory());
        expressionTypes.add(new AtanExpression.Factory());
        expressionTypes.add(new LogExpression.Factory());
        expressionTypes.add(new PerlinColorExpression.Factory());
        expressionTypes.add(new PerlinBWExpression.Factory());
        expressionTypes.add(new rgbToYCrCbExpression.Factory());
        expressionTypes.add(new yCrCbtoRGBExpression.Factory());
        expressionTypes.add(new MaxExpression.Factory());
        expressionTypes.add(new MinExpression.Factory());
        expressionTypes.add(new SumExpression.Factory());
        expressionTypes.add(new ProductExpression.Factory());
        expressionTypes.add(new AverageExpression.Factory());
        expressionTypes.add(new IfExpression.Factory());
}

    public Expression makeExpression (String input, HashMap<String, Expression> varMap)
    {
        myInput = input;
        myCurrentPosition = 0;
        Expression result = parseExpression(varMap);
        skipWhiteSpace();
        if (notAtEndOfString())
        {
            throw new ParserException("Unexpected characters at end of the string: " +
                                      stringAtCurrentPosition(),
                                      ParserException.Type.EXTRA_CHARACTERS);
        }
        return result;
    }



    public Expression parseExpression (HashMap<String, Expression> varMap)
    {
        skipWhiteSpace();
        for(Expression.Factory type : expressionTypes) {
            if(type.isKindOfExpression(this, varMap)) {
                return type.parseExpression(this, varMap);
            }
        }
        throw new ParserException("Unparsable expression: " + stringAtCurrentPosition());
    }


    public String stringAtCurrentPosition() {
        return myInput.substring(myCurrentPosition);
    }


    public void skipWhiteSpace ()
    {
        while (notAtEndOfString() && Character.isWhitespace(currentCharacter()))
        {
            myCurrentPosition++;
        }
    }

    public char currentCharacter ()
    {
        return myInput.charAt(myCurrentPosition);
    }

    public void advanceCurrentPosition(int chars)
    {
        myCurrentPosition += chars;
    }
    
    public boolean notAtEndOfString ()
    {
        return myCurrentPosition < myInput.length();
    }
}
