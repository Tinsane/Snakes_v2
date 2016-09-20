package Snake;

import MapObjects.DynamicMapObjects.SnakeCell;
import Utils.VelocityVector;

import java.util.Iterator;

/**
 * Created by Владимир on 16.09.2016.
 */
public class Snake implements Iterable<SnakeCell>
{
    public SnakeCell head;
    public SnakeCell tail;
    private boolean isDestructed = false;

    public Snake(int length)
    {
        this(length, new SnakeCell(null));
    }

    public Snake(int length, SnakeCell head)
    {
        if (length <= 0)
            throw new IllegalArgumentException();
        tail = this.head = head;
        extend(length - 1);
    }

    public void setVelocity(VelocityVector vector)
    {
        head.setVelocity(vector);
    }

    public int getLength()
    {
        int length = 0;
        for (SnakeCell cell : this)
            ++length;
        return length;
    }

    public void incLength()
    {
        tail.previous = new SnakeCell(null);
        tail = tail.previous;
    }

    public void extend(int length)
    {
        if (length < 0)
            throw new IllegalArgumentException();
        for (int i = 0; i < length; ++i)
            incLength();
    }

    public boolean getIsDestructed()
    {
        return isDestructed;
    }

    public void setIsDestructed(boolean isDestructed)
    {
        this.isDestructed = isDestructed;
        for (SnakeCell cell : this)
            cell.setIsDestructed(isDestructed);
    }

    @Override
    public Iterator<SnakeCell> iterator()
    {
        return new SnakeCellIterator(head);
    }
}
