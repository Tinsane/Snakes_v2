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
        for (SnakeCell current = head; current != null; current = current.previous)
            current.setIsDestructed(isDestructed);
    }
}
