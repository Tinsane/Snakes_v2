package Core.MapObjects.StaticMapObjects.Berries;

import Core.MapObjects.DynamicMapObjects.*;
import Core.MapObjects.*;
import Core.MapObjects.StaticMapObjects.*;
import Core.Game.Game;

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

    @Override
    public void processCollision(MapObject mapObject, Game game)
    {
        mapObject.processCollision(this, game);
    }

    @Override
    public void processCollision(SnakeCell snakeCell, Game game)
    {
        snakeCell.processCollision(this, game);
    }

    @Override
    public void processCollision(CatDogCell catDogCell, Game game)
    {
        catDogCell.processCollision(this, game);
    }
}
