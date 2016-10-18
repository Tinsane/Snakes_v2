package Core.MapObjects.StaticMapObjects;

import Core.MapObjects.DynamicMapObjects.*;
import Core.MapObjects.*;
import Core.Game.Game;
import View.Styles.GameStyle;

import java.awt.*;
import java.util.function.Consumer;

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
    public void processCollision(MapObject mapObject, Game game)
    {
        mapObject.sandGlassProcessCollision(this, game);
    }

    @Override
    public void snakeCellProcessCollision(SnakeCell snakeCell, Game game)
    {
        snakeCell.sandGlassProcessCollision(this, game);
    }

    @Override
    public void draw(GameStyle style, Game game, Consumer<Image> drawer)
    {
        style.draw(this, game, drawer);
    }
}
