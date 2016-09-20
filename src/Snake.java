/**
 * Created by Владимир on 16.09.2016.
 */
public class Snake
{
    SnakeCell head;
    SnakeCell tail;
    private boolean isDestructed = false;

    public Snake(int length)
    {
        if (length <= 0)
            throw new IllegalArgumentException();
        head = new SnakeCell(null);
        tail = head;
        extend(length - 1);
    }

    public Snake(int length, SnakeCell head)
    {
        if (length <= 0)
            throw new IllegalArgumentException();
        this.head = head;
        tail = head;
        extend(length - 1);
    }

    public void setVelocity(VelocityVector vector)
    {
        head.setVelocity(vector);
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
        SnakeCell current = head;
        while (current != null)
        {
            current.setIsDestructed(isDestructed);
            current = current.previous;
        }
    }
}
