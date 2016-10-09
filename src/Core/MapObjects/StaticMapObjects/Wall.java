package Core.MapObjects.StaticMapObjects;

import Core.MapObjects.DynamicMapObjects.*;
import Core.MapObjects.*;
import Core.Game.Game;
import View.Styles.GameStyle;

import javax.swing.*;

/**
 * Created by Владимир on 16.09.2016.
 */
public class Wall extends StaticMapObject
{
    public Wall()
    {
    }

    @Override
    public void processCollision(MapObject mapObject, Game game)
    {
        mapObject.wallProcessCollision(this, game);
    }

    @Override
    public void snakeCellProcessCollision(SnakeCell snakeCell, Game game)
    {
        snakeCell.wallProcessCollision(this, game);
    }

    @Override
    public Icon getIcon(GameStyle style, Game game)
    {
        return style.getWallIcon(this, game);
    }
}
