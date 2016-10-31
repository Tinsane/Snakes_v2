package Views.Styles;

import java.awt.*;

/**
 * Created by Владимир on 30.10.2016.
 */
public final class MenuStyle
{
    public static Font getFont(int size)
    {
        return new Font("Tahoma", Font.BOLD, size);
    }
    public static final Color textColor = Color.YELLOW;
    public static final Color pressedButtonColor = new Color(255, 215, 0);
    public static final Color backgroundColor = Color.GREEN;
}
