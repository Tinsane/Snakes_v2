package MapObjects.StaticMapObjects;

import MapObjects.DynamicMapObjects.*;
import MapObjects.*;
import MapObjects.StaticMapObjects.*;
import MapObjects.StaticMapObjects.Berries.*;
import Game.Game;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Владимир on 16.09.2016.
 */
public class Wall extends StaticMapObject
{
    public Wall()
    {
    }

    @Override
    public void processCollision(SnakeCell snakeCell, Game game)
    {
        snakeCell.processCollision(this, game);
    }

    @Override
    public void processCollision(Berry berry, Game game)
    {
        throw new NotImplementedException(); // TODO: shouldn't happen
    }

    @Override
    public void processCollision(SandGlass sandGlass, Game game)
    {
        throw new NotImplementedException(); // TODO: shouldn't happen
    }

    @Override
    public void processCollision(Wall wall, Game game)
    {
        throw new NotImplementedException(); // TODO: shouldn't happen
    }
}
