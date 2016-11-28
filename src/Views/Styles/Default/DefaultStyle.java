package Views.Styles.Default;

import Core.Game.GameAlike;
import Views.Styles.BaseGameStyle;
import Views.Styles.Drawer;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by ISmir on 18.10.2016.
 */
public class DefaultStyle extends BaseGameStyle
{
    public final BufferedImage wallImage;
    public final BufferedImage sandGlassImage;
    public final BufferedImage strawberryImage;
    public final BufferedImage blueberryImage;
    public final BufferedImage snakeCellImage;
    public final BufferedImage emptyCellImage;
    public final BufferedImage snakeHeadImage;
    public final BufferedImage catImage;
    public final BufferedImage dogImage;
    public final BufferedImage catDogBodyImage;
    public final BufferedImage catDogLegsImage;

    public DefaultStyle() throws IOException
    {
        wallImage = loadImage("wall.bmp");
        sandGlassImage = null;
        strawberryImage = loadImage("strawberry.png");
        blueberryImage = loadImage("blueberry.png");
        snakeCellImage = loadImage("snake_body_thick.png");
        emptyCellImage = loadImage("empty_cell.bmp");
        snakeHeadImage = loadImage("head.png");
        catImage = loadImage("cat_face.png");
        dogImage = loadImage("dog_face.png");
        catDogBodyImage = loadImage("cat_dog_body.png");
        catDogLegsImage = loadImage("body_with_legs.png");
    }

    @Override
    public Drawer CreateDrawer(Graphics2D g2d, GameAlike game, double turnPartLeft)
    {
        return new DefaultDrawer(this, g2d, game, turnPartLeft);
    }
}
