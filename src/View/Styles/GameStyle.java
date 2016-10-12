package View.Styles;

import Core.Game.Game;
import Core.MapObjects.DynamicMapObjects.SnakeCell;
import Core.MapObjects.StaticMapObjects.Berries.Blueberry;
import Core.MapObjects.StaticMapObjects.Berries.Strawberry;
import Core.MapObjects.StaticMapObjects.EmptyCell;
import Core.MapObjects.StaticMapObjects.SandGlass;
import Core.MapObjects.StaticMapObjects.Wall;

import java.awt.*;

/**
 * Created by ISmir on 08.10.2016.
 */
public interface GameStyle
{
    Image getWallImage(Wall wall, Game game);
    Image getSandGlassImage(SandGlass sandGlass, Game game);
    Image getStrawberryImage(Strawberry strawberry, Game game);
    Image getBlueberryImage(Blueberry blueberry, Game game);
    Image getSnakeCellImage(SnakeCell snakeCell, Game game);
    Image getEmptyCellImage(EmptyCell emptyCell, Game game);
    int getTileSize();
}
