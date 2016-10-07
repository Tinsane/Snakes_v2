package MapObjects.StaticMapObjects;

import MapObjects.DynamicMapObjects.*;
import MapObjects.*;
import Game.Game;

/**
 * Created by Владимир on 16.09.2016.
 */
public class SandGlass extends StaticMapObject
{
    private final int rollbackTurnsNumber;

    public SandGlass(int rollbackTurnsNumber)
    {
        this.rollbackTurnsNumber = rollbackTurnsNumber;
    }

    public int getRollbackTurnsNumber()
    {
        return rollbackTurnsNumber;
    }

    @Override
    public void processCollision(MapObject visitor, Game game)
    {
        visitor.sandGlassProcessCollision(this, game);
    }

    @Override
    public void snakeCellProcessCollision(SnakeCell snakeCell, Game game)
    {
        snakeCell.sandGlassProcessCollision(this, game);
    }
}
