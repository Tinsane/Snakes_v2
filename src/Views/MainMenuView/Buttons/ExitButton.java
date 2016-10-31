package Views.MainMenuView.Buttons;

import Views.Utils.MenuButton;

import javax.swing.*;

/**
 * Created by Владимир on 31.10.2016.
 */
public class ExitButton extends MenuButton
{
    public ExitButton(String text, int fontSize, JFrame parent)
    {
        super(text, fontSize);
        addActionListener(e -> parent.dispose());
    }
}
