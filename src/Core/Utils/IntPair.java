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

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null || !(obj instanceof IntPair)) return false;
        IntPair pair = (IntPair) obj;
        return pair.x == x && pair.y == y;
    }

    public IntPair getAdded(IntPair pair)
    {
        return new IntPair(x + pair.x, y + pair.y);
    }
}
