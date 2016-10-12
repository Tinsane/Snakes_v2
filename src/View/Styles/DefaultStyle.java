package View.Styles;

import Core.Game.Game;
import Core.MapObjects.DynamicMapObjects.SnakeCell;
import Core.MapObjects.StaticMapObjects.Berries.Blueberry;
import Core.MapObjects.StaticMapObjects.Berries.Strawberry;
import Core.MapObjects.StaticMapObjects.EmptyCell;
import Core.MapObjects.StaticMapObjects.SandGlass;
import Core.MapObjects.StaticMapObjects.Wall;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Created by ISmir on 08.10.2016.
 */
public class DefaultStyle implements GameStyle
{
    private final Image wallImage;
    private final Image sandGlassImage;
    private final Image strawberryImage;
    private final Image blueberryImage;
    private final Image snakeCellImage;
    private final Image emptyCellImage;
    public DefaultStyle() throws IOException
    {
        wallImage = ImageIO.read(getClass().getResource("wall.bmp"));
        sandGlassImage = null;
        strawberryImage = ImageIO.read(getClass().getResource("kekberry_v2.bmp"));
        blueberryImage = ImageIO.read(getClass().getResource("blueberry.bmp"));
        snakeCellImage = ImageIO.read(getClass().getResource("snake_body.bmp"));
        emptyCellImage = ImageIO.read(getClass().getResource("empty_cell.bmp"));
    }

    @Override
    public Image getWallImage(Wall wall, Game game)
    {
        return wallImage;
    }

    @Override
    public Image getSandGlassImage(SandGlass sandGlass, Game game)
    {
        return sandGlassImage;
    }

    @Override
    public Image getStrawberryImage(Strawberry strawberry, Game game)
    {
        return strawberryImage;
    }

    @Override
    public Image getBlueberryImage(Blueberry blueberry, Game game)
    {
        return blueberryImage;
    }

    @Override
    public Image getSnakeCellImage(SnakeCell snakeCell, Game game)
    {
        return snakeCellImage;
    }

    @Override
    public Image getEmptyCellImage(EmptyCell emptyCell, Game game)
    {
        return emptyCellImage;
    }
}
