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
        throw new NotImplementedException();
        // TODO: reflection here
    }
}
