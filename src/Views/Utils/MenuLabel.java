package Views.Utils;

import Views.Styles.MenuStyle;

import javax.swing.*;

/**
 * Created by ISmir on 21.11.2016.
 */
public class MenuLabel extends JLabel
{
    public MenuLabel(String text, int fontSize)
    {
        super(text, SwingConstants.CENTER);
        setForeground(MenuStyle.TEXT_COLOR);
        setFont(MenuStyle.getFont(fontSize));
    }
}
