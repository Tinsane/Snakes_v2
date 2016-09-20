import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by ISmir on 17.09.2016.
 */
public abstract class BaseMapObject implements MapObject
{
    public boolean isDestructed = false;

    @Override
    public boolean getIsDestructed()
    {
        return isDestructed;
    }

    public void setIsDestructed(boolean isDestructed)
    {
        this.isDestructed = isDestructed;
    }

    @Override
    public void processCollision(MapObject collidedObject, Game game)
    {
        Class<? extends MapObject> collidedObjectClass = collidedObject.getClass();
        if (collidedObjectClass == SnakeCell.class)
            this.processCollision((SnakeCell) collidedObject, game);
        else if (collidedObjectClass == Blueberry.class)
            this.processCollision((Blueberry) collidedObject, game);
        else if (collidedObjectClass == Strawberry.class)
            this.processCollision((Strawberry) collidedObject, game);
        else if (collidedObjectClass == Wall.class)
            this.processCollision((Wall) collidedObject, game);
        else if (collidedObjectClass == SandGlass.class)
            this.processCollision((SandGlass) collidedObject, game);
        else
            throw new NotImplementedException();
        // TODO: reflection here
    }
}
