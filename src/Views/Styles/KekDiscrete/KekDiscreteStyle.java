package Views.Styles.KekDiscrete;

import Core.Game.GameAlike;
import Core.MapObjects.MapObjectVisitor;
import Views.Styles.Drawer;
import Views.Styles.GameStyle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by ISmir on 18.10.2016.
 */
public class KekDiscreteStyle implements GameStyle
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

    public KekDiscreteStyle() throws IOException
    {
        wallImage = loadImage("wall.jpg");
        sandGlassImage = null;
        strawberryImage = loadImage("golden_sugar.jpg");
        blueberryImage = loadImage("sugar.jpg");
        snakeCellImage = loadImage("snake_body.bmp");
        emptyCellImage = loadImage("emptyCell.jpg");
        snakeHeadImage = loadImage("Yura.jpg");
    }

    @Override
    public Drawer CreateDrawer(Graphics2D g2d, GameAlike game, double turnPartLeft)
    {
        return new KekDiscreteDrawer(this, g2d, game, turnPartLeft);
    }

    public int getTileSize()
    {
        return 50;
    }
}
