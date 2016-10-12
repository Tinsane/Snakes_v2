package Core.MapObjects.StaticMapObjects;

import Core.Game.Game;
import Core.MapObjects.DynamicMapObjects.SnakeCell;
import Core.MapObjects.MapObject;
import Core.MapObjects.StaticMapObjects.Berries.Berry;
import Core.Utils.VelocityVector;
import View.Styles.GameStyle;

import java.awt.*;

/**
 * Created by ISmir on 09.10.2016.
 */
public class EmptyCell extends StaticMapObject
{
    @Override
    public Image getImage(GameStyle style, Game game)
    {
        return style.getEmptyCellImage(this, game);
    }

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
    public void berryProcessCollision(Berry berry, Game game)
    {
        berry.emptyCellProcessCollision(this, game);
    }

    @Override
    public void snakeCellProcessCollision(SnakeCell snakeCell, Game game)
    {
        snakeCell.emptyCellProcessCollision(this, game);
    }

    @Override
    public void wallProcessCollision(Wall wall, Game game)
    {
        wall.emptyCellProcessCollision(this, game);
    }

    @Override
    public void sandGlassProcessCollision(SandGlass sandGlass, Game game)
    {
        sandGlass.emptyCellProcessCollision(this, game);
    }
}
