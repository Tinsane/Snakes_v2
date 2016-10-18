package Core.MapObjects.StaticMapObjects;

import Core.Game.Game;
import Core.MapObjects.DynamicMapObjects.SnakeCell;
import Core.MapObjects.MapObject;
import Core.MapObjects.StaticMapObjects.Berries.Berry;
import Core.Utils.VelocityVector;
import View.Styles.GameDrawer;

import java.awt.*;
import java.util.function.Consumer;

/**
 * Created by ISmir on 09.10.2016.
 */
public class EmptyCell extends StaticMapObject
{

    @Override
    public VelocityVector getVelocity()
    {
        return null;
    }

    @Override
    public boolean getIsDestructed()
    {
        return false;
    }

    @Override
    public void processCollision(MapObject mapObject, Game game)
    {
        mapObject.processCollision(this, game);
    }

    @Override
    public void processCollision(Berry berry, Game game)
    {
        berry.processCollision(this, game);
    }

    @Override
    public void processCollision(SnakeCell snakeCell, Game game)
    {
        snakeCell.processCollision(this, game);
    }

    @Override
    public void processCollision(Wall wall, Game game)
    {
        wall.processCollision(this, game);
    }

    @Override
    public void processCollision(SandGlass sandGlass, Game game)
    {
        sandGlass.processCollision(this, game);
    }

    @Override
    public void draw(GameDrawer drawer)
    {
        drawer.draw(this);
    }
}