package View.Styles;

import Core.Game.Game;
import Core.MapObjects.DynamicMapObjects.SnakeCell;
import Core.MapObjects.StaticMapObjects.Berries.Blueberry;
import Core.MapObjects.StaticMapObjects.Berries.Strawberry;
import Core.MapObjects.StaticMapObjects.SandGlass;
import Core.MapObjects.StaticMapObjects.Wall;

import javax.swing.*;

/**
 * Created by ISmir on 08.10.2016.
 */
public interface GameStyle
{
    Icon getWallIcon(Wall wall, Game game);
    Icon getSandGlassIcon(SandGlass sandGlass, Game game);
    Icon getStrawberryIcon(Strawberry strawberry, Game game);
    Icon getBlueberryIcon(Blueberry blueberry, Game game);
    Icon getSnakeCellIcon(SnakeCell snakeCell, Game game);
}
