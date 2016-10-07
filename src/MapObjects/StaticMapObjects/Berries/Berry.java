package MapObjects.StaticMapObjects.Berries;

import MapObjects.DynamicMapObjects.*;
import MapObjects.*;
import MapObjects.StaticMapObjects.*;
import Game.Game;

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
    public void processCollision(MapObject visitor, Game game)
    {
        visitor.berryProcessCollision(this, game);
    }

    @Override
    public void snakeCellProcessCollision(SnakeCell snakeCell, Game game)
    {
        snakeCell.berryProcessCollision(this, game);
    }
}
