import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Владимир on 16.09.2016.
 */
public class SandGlass extends StaticMapObject
{
    public final int rollbackTurnsNumber;

    public SandGlass(int rollbackTurnsNumber)
    {
        this.rollbackTurnsNumber = rollbackTurnsNumber;
    }

    public void processCollision(SnakeCell snakeCell, Game game)
    {
        snakeCell.processCollision(this, game);
    }

    public void processCollision(Berry berry, Game game)
    {
        throw new NotImplementedException(); // TODO: shouldn't happen
    }

    public void processCollision(SandGlass sandGlass, Game game)
    {
        throw new NotImplementedException(); // TODO: shouldn't happen
    }

    public void processCollision(Wall wall, Game game)
    {
        throw new NotImplementedException(); // TODO: shouldn't happen
    }
}
