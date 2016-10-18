package View.Styles;

import Core.Game.Game;
import Core.MapObjects.DynamicMapObjects.SnakeCell;
import Core.MapObjects.MapObject;
import Core.MapObjects.StaticMapObjects.Berries.Blueberry;
import Core.MapObjects.StaticMapObjects.Berries.Strawberry;
import Core.MapObjects.StaticMapObjects.EmptyCell;
import Core.MapObjects.StaticMapObjects.SandGlass;
import Core.MapObjects.StaticMapObjects.Wall;

import java.awt.*;
import java.util.function.Consumer;

/**
 * Created by ISmir on 08.10.2016.
 */
public interface GameDrawer
{
    void draw(Wall wall);
    void draw(SandGlass sandGlass);
    void draw(Strawberry strawberry);
    void draw(Blueberry blueberry);
    void draw(SnakeCell snakeCell);
    void draw(EmptyCell emptyCell);

    void draw();
}
