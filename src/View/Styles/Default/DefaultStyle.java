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
    public GameDrawer CreateDrawer(Graphics2D g2d, Game game, double turnPartLeft)
    {
        return new DefaultDrawer(this, g2d, game, turnPartLeft);
    }

    public int getTileSize()
    {
        return 50;
    }
}
