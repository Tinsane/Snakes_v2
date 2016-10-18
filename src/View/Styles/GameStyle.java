package View.Styles;

import Core.Game.Game;
import Core.MapObjects.DynamicMapObjects.SnakeCell;
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
public interface GameStyle
{
    void draw(Wall wall, Game game, Consumer<Image> drawer);
    void draw(SandGlass sandGlass, Game game, Consumer<Image> drawer);
    void draw(Strawberry strawberry, Game game, Consumer<Image> drawer);
    void draw(Blueberry blueberry, Game game, Consumer<Image> drawer);
    void draw(SnakeCell snakeCell, Game game, Consumer<Image> drawer);
    void draw(EmptyCell emptyCell, Game game, Consumer<Image> drawer);
    int getTileSize();
}
