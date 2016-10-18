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
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.function.Consumer;

/**
 * Created by ISmir on 08.10.2016.
 */
public class DefaultStyle implements GameStyle
{
    private final BufferedImage wallImage;
    private final BufferedImage sandGlassImage;
    private final BufferedImage strawberryImage;
    private final BufferedImage blueberryImage;
    private final BufferedImage snakeCellImage;
    private final BufferedImage emptyCellImage;
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
    public void draw(Wall wall, Game game, Consumer<Image> drawer)
    {
        drawer.accept(wallImage);
    }

    @Override
    public void draw(SandGlass sandGlass, Game game, Consumer<Image> drawer)
    {
        drawer.accept(sandGlassImage);
    }

    @Override
    public void draw(Strawberry strawberry, Game game, Consumer<Image> drawer)
    {
        drawer.accept(strawberryImage);
    }

    @Override
    public void draw(Blueberry blueberry, Game game, Consumer<Image> drawer)
    {
        drawer.accept(blueberryImage);
    }

    @Override
    public void draw(SnakeCell snakeCell, Game game, Consumer<Image> drawer)
    {
        drawer.accept(snakeCellImage);
    }

    @Override
    public void draw(EmptyCell emptyCell, Game game, Consumer<Image> drawer)
    {
        drawer.accept(emptyCellImage);
    }

    @Override
    public int getTileSize()
    {
        return 50;
    }
}
