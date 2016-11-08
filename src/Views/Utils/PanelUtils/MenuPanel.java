package Views.Utils.PanelUtils;

import Views.Styles.MenuStyle;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Владимир on 01.11.2016.
 */
public class MenuPanel extends JPanel
{
    public MenuPanel(Component components[])
    {
        super();
        setBackground(MenuStyle.BACKGROUND_COLOR);
        setLayout(new GridLayout(components.length, 1));
        for(Component component : components)
            add(component);
    }
}
