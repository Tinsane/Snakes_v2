package Views.Utils;

import java.awt.*;

/**
 * Created by Владимир on 21.11.2016.
 */
public class ColorUtils
{
    public static Color GetWithSpecifiedTransparency(Color color, int alpha)
    {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }
}
