package MapObjects.StaticMapObjects.Berries;

import MapObjects.BaseMapObject;
import MapObjects.DynamicMapObjects.*;
import MapObjects.*;
import MapObjects.StaticMapObjects.*;
import MapObjects.StaticMapObjects.Berries.*;
import Game.Game;
import Utils.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Владимир on 16.09.2016.
 */

public abstract class Berry extends StaticMapObject
{
    private final int satisfactionCoefficient;

    public Berry(int satisfactionCoefficient)
    {
        this.satisfactionCoefficient = satisfactionCoefficient;
    }

    public int getSatisfactionCoefficient()
    {
        return satisfactionCoefficient;
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
