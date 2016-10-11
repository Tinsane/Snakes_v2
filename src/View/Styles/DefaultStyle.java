package View.Styles;

import Core.Game.Game;
import Core.MapObjects.DynamicMapObjects.SnakeCell;
import Core.MapObjects.StaticMapObjects.Berries.Blueberry;
import Core.MapObjects.StaticMapObjects.Berries.Strawberry;
import Core.MapObjects.StaticMapObjects.EmptyCell;
import Core.MapObjects.StaticMapObjects.SandGlass;
import Core.MapObjects.StaticMapObjects.Wall;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by ISmir on 08.10.2016.
 */
public class DefaultStyle implements GameStyle
{
    @Override
    public Image getWallIcon(Wall wall, Game game)
    {
        try
        {
        return ImageIO.read(getClass().getResource("wall.bmp"));
    }
        catch (IOException e)
        {
            // TODO: logging here
        }
        return null;
    }

    @Override
    public Image getSandGlassIcon(SandGlass sandGlass, Game game)
    {
        try
        {
            return ImageIO.read(getClass().getResource("sandglass.bmp"));
        }
        catch (IOException e)
        {
            // TODO: logging here
        }
        return null;
    }

    @Override
    public Image getStrawberryIcon(Strawberry strawberry, Game game)
    {
        try
        {
            return ImageIO.read(getClass().getResource("kekberry_v2.bmp"));
        }
        catch (IOException e)
        {
            // TODO: logging here
        }
        return null;
    }

    @Override
    public Image getBlueberryIcon(Blueberry blueberry, Game game)
    {
        throw new NotImplementedException();
    }

    @Override
    public Image getSnakeCellIcon(SnakeCell snakeCell, Game game)
    {
        try
        {
            return ImageIO.read(getClass().getResource("snake_body.bmp"));
        }
        catch (IOException e)
        {
            // TODO: logging here
        }
        return null;
    }

    @Override
    public Image getEmptyCellIcon(EmptyCell emptyCell, Game game)
    {
        try
        {
            return ImageIO.read(getClass().getResource("empty_cell.bmp"));
        }
        catch (IOException e)
        {
            // TODO: logging here
        }
        return null;
    }
}
