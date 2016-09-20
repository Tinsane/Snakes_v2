package MapObjects;

import MapObjects.DynamicMapObjects.*;
import MapObjects.*;
import MapObjects.StaticMapObjects.*;
import MapObjects.StaticMapObjects.Berries.*;
import Snake.Snake;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import Game.Game;

import java.lang.reflect.Method;

/**
 * Created by ISmir on 17.09.2016.
 */
public abstract class BaseMapObject implements MapObject
{
    public boolean isDestructed = false;

    private Class[] possibleClasses = new Class[]{SnakeCell.class, Berry.class, SandGlass.class, Wall.class};

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
            Class collidedObjectClass = collidedObject.getClass();
            for (Class possibleClass: possibleClasses)
                if (possibleClass.isAssignableFrom(collidedObjectClass))
                {
                    Method processCollision = objectClass.getMethod("processCollision", possibleClass, Game.class);
                    processCollision.invoke(this, collidedObject, game);
                }
        }
        catch (Exception e)
        {
            System.out.println("Problems with processing collision.");
            throw new IllegalArgumentException();
        }
    }
}
