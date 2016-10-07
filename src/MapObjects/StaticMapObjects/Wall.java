package MapObjects.StaticMapObjects;

import MapObjects.DynamicMapObjects.*;
import MapObjects.*;
import Game.Game;

/**
 * Created by Владимир on 16.09.2016.
 */
public class Wall extends StaticMapObject
{
    public Wall()
    {
    }

    @Override
    public void processCollision(MapObject visitor, Game game)
    {
        visitor.wallProcessCollision(this, game);
    }

    @Override
    public void snakeCellProcessCollision(SnakeCell snakeCell, Game game)
    {
        snakeCell.wallProcessCollision(this, game);
    }
}
