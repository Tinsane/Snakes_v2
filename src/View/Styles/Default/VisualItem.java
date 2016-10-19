package View.Styles.Default;

import java.awt.image.BufferedImage;

/**
 * Created by Владимир on 19.10.2016.
 */
public class VisualItem
{
    public final BufferedImage image;
    public final double x, y;
    public final Integer priority;
    public VisualItem(BufferedImage image, double x, double y, int priority)
    {
        this.image = image;
        this.x = x;
        this.y = y;
        this.priority = priority;
    }
}
