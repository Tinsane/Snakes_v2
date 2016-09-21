package Utils;

/**
 * Created by Владимир on 16.09.2016.
 */
public class VelocityVector
{
    // these are all the possibles vectors
    public static final VelocityVector zero = new VelocityVector(0, 0);
    public static final VelocityVector up = new VelocityVector(0, -1);
    public static final VelocityVector down = new VelocityVector(0, 1);
    public static final VelocityVector left = new VelocityVector(-1, 0);
    public static final VelocityVector right = new VelocityVector(1, 0);

    public int x;
    public int y;

    VelocityVector(int x, int y) // TODO: maybe better to make it private
    {
        if (Math.abs(x) + Math.abs(y) > 1)
            throw new IllegalArgumentException("x and y should be coordinates of axis parallel unit or zero vector");
        this.x = x;
        this.y = y;
    }

    private static boolean inRange(int x, int rangeLowerBound, int rangeUpperBound)
    {
        return rangeLowerBound <= x && x <= rangeUpperBound;
    }

    public VelocityVector getReversed()
    {
        return new VelocityVector(-x, -y);
    }

    public IntPair getIntPair()
    {
        return new IntPair(x, y);
    }
}
