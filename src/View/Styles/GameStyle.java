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
    Image getWallIcon(Wall wall, Game game);
    Image getSandGlassIcon(SandGlass sandGlass, Game game);
    Image getStrawberryIcon(Strawberry strawberry, Game game);
    Image getBlueberryIcon(Blueberry blueberry, Game game);
    Image getSnakeCellIcon(SnakeCell snakeCell, Game game);
    Image getEmptyCellIcon(EmptyCell emptyCell, Game game);
}
