package MapObjects;

import MapObjects.DynamicMapObjects.*;
import MapObjects.*;
import MapObjects.StaticMapObjects.*;
import MapObjects.StaticMapObjects.Berries.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import Game.Game;

import java.lang.reflect.Method;

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

    public abstract void processCollision(SnakeCell collidedObject, Game game);
    public abstract void processCollision(Berry collidedObject, Game game);
    public abstract void processCollision(SandGlass collidedObject, Game game);
    public abstract void processCollision(Wall collidedObject, Game game);

    @Override
    public void processCollision(MapObject collidedObject, Game game)
    {
        try
        {
            Class objectClass = getClass();
            Method processCollision = objectClass.getMethod("processCollision", collidedObject.getClass(), Game.class);
            processCollision.invoke(this, collidedObject, game);
        }
        catch (Exception e)
        {
            System.out.println("Problems with processing collision.");
        }
    }
}
