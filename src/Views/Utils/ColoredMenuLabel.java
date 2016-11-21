package Views.Utils;

import Views.Styles.MenuStyle;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Владимир on 21.11.2016.
 */
public class ColoredMenuLabel extends JLabel
{
    public ColoredMenuLabel(String text, int fontSize, Color textColor)
    {
        super(text, SwingConstants.CENTER);
        setForeground(textColor);
        setFont(MenuStyle.getFont(fontSize));
    }
}
