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

    @Override
    public boolean getIsDestructed()
    {
        return isDestructed;
    }

    public void setIsDestructed(boolean isDestructed)
    {
        this.isDestructed = isDestructed;
    }
}
