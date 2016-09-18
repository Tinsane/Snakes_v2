import javafx.util.Pair;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Владимир on 16.09.2016.
 */
public class Snake
{
    SnakeCell head;
    SnakeCell tail;

    public Snake()
    {
        throw new NotImplementedException();
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
            current = current.getPrevious();
        }
    }

    public void extend(int length)
    {
        throw new NotImplementedException();
    }

    public boolean getIsDestructed()
    {
        return isDestructed;
    }
}
