package Views.Styles;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by ISmir on 05.11.2016.
 */
public abstract class BaseGameStyle implements GameStyle
{
    protected BufferedImage loadImage(String filename) throws IOException
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

    public int getTileSize()
    {
        return 50;
    }
}
