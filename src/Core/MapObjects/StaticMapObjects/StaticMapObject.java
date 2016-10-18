package Core.MapObjects.StaticMapObjects;

import Core.MapObjects.BaseMapObject;
import Core.MapObjects.StaticMapObjects.Berries.*;
import Core.Game.Game;
import Core.Utils.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by ISmir on 17.09.2016.
 */
public abstract class StaticMapObject extends BaseMapObject
{
    @Override
    public VelocityVector getVelocity()
    {
        return VelocityVector.zero;
    }

    @Override
    public void processCollision(Wall wall, Game game)
    {
        throw new NotImplementedException();
    }

    @Override
    public void processCollision(SandGlass sandGlass, Game game)
    {
        throw new NotImplementedException();
    }

    @Override
    public void processCollision(Berry berry, Game game)
    {
        throw new NotImplementedException();
    }
}
