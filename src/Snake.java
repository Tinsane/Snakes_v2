import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Владимир on 16.09.2016.
 */
public class Snake
{
    SnakeCell first;
    SnakeCell last;

    public Snake()
    {
        throw new NotImplementedException();
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
