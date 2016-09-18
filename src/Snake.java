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
        //TODO: destruct all the snake cells
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
