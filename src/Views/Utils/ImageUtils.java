package Views.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Владимир on 21.11.2016.
 */
public class ImageUtils
{
    public static BufferedImage getDyed(BufferedImage image, Color color)
    {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage dyed = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = dyed.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.setComposite(AlphaComposite.SrcAtop);
        g.setColor(color);
        g.fillRect(0, 0, w, h);
        g.dispose();
        return dyed;
    }
}
