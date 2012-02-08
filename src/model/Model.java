package model;

import java.awt.Dimension;
import java.util.HashMap;

import model.expression.Expression;


/**
 * Evaluate an expression for each pixel in a image.
 * 
 * @author Robert C Duvall
 */
public class Model
{
    public static final double DOMAIN_MIN = -1;
    public static final double DOMAIN_MAX = 1;
    public static final int NUM_FRAMES = 50;

    private double evalX;
    private double evalY;
    private double myCurrentTime = -1;

    public void reset ()
    {
        myCurrentTime = -1;
    }

    public void nextFrame ()
    {
        myCurrentTime += 2.0 / NUM_FRAMES;
    }

    public Pixmap evaluate (String input, Dimension size)
    {
        Pixmap result = new Pixmap(size);
        HashMap<String, Expression> varMap = new HashMap<String, Expression>();
        Expression toEval = new Parser().makeExpression(input, varMap);
        
        for (int imageY = 0; imageY < size.height; imageY++)
        {
            evalY = imageToDomainScale(imageY, size.height);
            for (int imageX = 0; imageX < size.width; imageX++)
            {
                evalX = imageToDomainScale(imageX, size.width);
                result.setColor(imageX, imageY,
                                toEval.evaluate(varMap, evalX, evalY, myCurrentTime).toJavaColor());
            }
        }
        return result;
    }


    /**
     * Convert from image space to domain space.
     */
    protected double imageToDomainScale (int value, int bounds)
    {
        double range = DOMAIN_MAX - DOMAIN_MIN;
        return ((double)value / bounds) * range + DOMAIN_MIN;
    }
}
