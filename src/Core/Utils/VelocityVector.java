package Core.Utils;

import java.io.Serializable;

/**
 * Created by Владимир on 16.09.2016.
 */
public class VelocityVector implements Serializable
{
    // these are all the possibles vectors
    public static final VelocityVector zero = new VelocityVector(0, 0);
    public static final VelocityVector up = new VelocityVector(0, -1);
    public static final VelocityVector down = new VelocityVector(0, 1);
    public static final VelocityVector left = new VelocityVector(-1, 0);
    public static final VelocityVector right = new VelocityVector(1, 0);
    public static final VelocityVector[] directions = new VelocityVector[] {
            VelocityVector.down,
            VelocityVector.up,
            VelocityVector.left,
            VelocityVector.right,
    };

    public int x;
    public int y;

    private VelocityVector(int x, int y)
    {
        if (Math.abs(x) + Math.abs(y) > 1)
            throw new IllegalArgumentException("x and y should be coordinates of axis parallel unit or zero vector");
        this.x = x;
        this.y = y;
    }

    public int getScalarProduct(VelocityVector v)
    {
        return x * v.x + y * v.y;
    }

    public int getCrossProduct(VelocityVector v)
    {
        return x * v.y - y * v.x;
    }

    public double getAngle(VelocityVector v)
    {
        return Math.atan2(getCrossProduct(v), getScalarProduct(v));
    }

    private static boolean inRange(int x, int rangeLowerBound, int rangeUpperBound)
    {
        return rangeLowerBound <= x && x <= rangeUpperBound;
    }

    public VelocityVector getReversed()
    {
        return new VelocityVector(-x, -y);
//        if(this == VelocityVector.zero)
//            return this;
//        else if (this == VelocityVector.down)
//            return VelocityVector.up;
//        else if (this == VelocityVector.up)
//            return VelocityVector.down;
//        else if (this == VelocityVector.left)
//            return VelocityVector.right;
//        else
//            return VelocityVector.left;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null || !(obj instanceof VelocityVector))
            return false;
        VelocityVector vector = (VelocityVector) obj;
        return x == vector.x && y == vector.y;
    }

    public IntPair getIntPair()
    {
        return new IntPair(x, y);
    }

    @Override
    public String toString()
    {
        return String.format("(%d, %d)", x, y);
    }
}
