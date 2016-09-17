/**
 * Created by Владимир on 16.09.2016.
 */
public class Vector
{
    public static final Vector zero = new Vector(0, 0);

    int x, y;
    Vector(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public Vector getReversed()
    {
        return new Vector(-x, -y);
    }
}
