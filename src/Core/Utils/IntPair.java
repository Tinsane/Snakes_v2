package Core.Utils;

import java.io.Serializable;

/**
 * Created by ISmir on 19.09.2016.
 */
public class IntPair implements Serializable
{
    public int x;
    public int y;

    public IntPair(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void add(IntPair pair)
    {
        x += pair.x;
        y += pair.y;
    }

    public IntPair getAdded(IntPair pair)
    {
        return new IntPair(x + pair.x, y + pair.y);
    }
}
