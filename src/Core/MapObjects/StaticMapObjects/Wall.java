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
public class Wall extends StaticMapObject
{
    public Wall()
    {
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
    public void draw(GameStyle style, Game game, Consumer<Image> drawer)
    {
        style.draw(this, game, drawer);
    }
}
