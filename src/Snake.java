import com.sun.javaws.exceptions.InvalidArgumentException;
import javafx.util.Pair;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Владимир on 16.09.2016.
 */
public class Snake
{
    SnakeCell head;
    SnakeCell tail;

    public Snake(int length)
    {
        if (length <= 0)
            throw new IllegalArgumentException();
        head = new SnakeCell(null);
        tail = head;
        extend(length - 1);
    }

    public Snake(SnakeCell head, SnakeCell tail)
    {
        this.head = head;
        this.tail = tail;
    }

    private boolean isDestructed = false;

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

    public void incLength()
    {
        SnakeCell newTail = new SnakeCell(null);
        tail.previous = newTail;
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
}
