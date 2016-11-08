package Views.Utils;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by ISmir on 08.11.2016.
 */
public class DimensionUtils
{
    public static Dimension placedVertically(Dimension... dimensions)
    {
        return Arrays
                .stream(dimensions)
                .reduce((x, y) -> new Dimension((int)Math.max(x.getWidth(), y.getWidth()),
                        (int)(x.getHeight() + y.getHeight())))
                .get();
    }
}
