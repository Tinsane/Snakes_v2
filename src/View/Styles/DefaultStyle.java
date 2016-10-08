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
public class DefaultStyle implements GameStyle
{
    @Override
    public Icon getWallIcon(Wall wall, Game game)
    {
        return null;
    }

    @Override
    public Icon getSandGlassIcon(SandGlass sandGlass, Game game)
    {
        return null;
    }

    @Override
    public Icon getStrawberryIcon(Strawberry strawberry, Game game)
    {
        return null;
    }

    @Override
    public Icon getBlueberryIcon(Blueberry blueberry, Game game)
    {
        return null;
    }

    @Override
    public Icon getSnakeCellIcon(SnakeCell snakeCell, Game game)
    {
        return null;
    }
}
