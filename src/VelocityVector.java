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

    int x;
    int y;

    VelocityVector(int x, int y) // TODO: maybe better to make it private
    {
        int absX = Math.abs(x);
        int absY = Math.abs(y);
        if ((x != 0 && y != 0) || absX > 1 || absY > 1)
            throw new IllegalArgumentException("x and y should be coordinates of vector parallel to axis and with length 1 or a zero vector");
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
}
