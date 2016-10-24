package Views.Styles.Default;

import Core.Game.GameAlike;
import Core.MapObjects.MapObjectVisitor;
import Views.Styles.GameStyle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by ISmir on 18.10.2016.
 */
public class DefaultStyle implements GameStyle
{
    public final BufferedImage wallImage;
    public final BufferedImage sandGlassImage;
    public final BufferedImage strawberryImage;
    public final BufferedImage blueberryImage;
    public final BufferedImage snakeCellImage;
    public final BufferedImage emptyCellImage;
    public final BufferedImage snakeHeadImage;

    private BufferedImage loadImage(String filename) throws IOException
    {
        try
        {
            return ImageIO.read(getClass().getResource(filename));
        }
        catch (IllegalArgumentException e)
        {
            throw new IOException("Error: pictures weren't found.");
        }
    }

    public DefaultStyle() throws IOException
    {
        wallImage = loadImage("wall.bmp");
        sandGlassImage = null;
        strawberryImage = loadImage("strawberry.png");
        blueberryImage = loadImage("blueberry.png");
        snakeCellImage = loadImage("snake_body_thick.png");
        emptyCellImage = loadImage("empty_cell.bmp");
        snakeHeadImage = loadImage("head.png");
    }

    @Override
    public MapObjectVisitor CreateDrawer(Graphics2D g2d, GameAlike game, double turnPartLeft)
    {
        return new DefaultDrawer(this, g2d, game, turnPartLeft);
    }

    public int getTileSize()
    {
        return 50;
    }
}
