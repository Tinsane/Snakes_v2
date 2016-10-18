package View.Styles.Default;

import Core.Game.Game;
import View.Styles.GameDrawer;
import View.Styles.GameStyle;

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

    public DefaultStyle() throws IOException
    {
        wallImage = ImageIO.read(getClass().getResource("wall.bmp"));
        sandGlassImage = null;
        strawberryImage = ImageIO.read(getClass().getResource("strawberry.png"));
        blueberryImage = ImageIO.read(getClass().getResource("blueberry.png"));
        snakeCellImage = ImageIO.read(getClass().getResource("snake_body.bmp"));
        emptyCellImage = ImageIO.read(getClass().getResource("empty_cell.bmp"));
    }

    @Override
    public GameDrawer CreateDrawer(Graphics2D g2d, Game game, double turnPartLeft)
    {
        return new DefaultDrawer(this, g2d, game, turnPartLeft);
    }

    public int getTileSize()
    {
        return 50;
    }
}
