package Views.MainMenuView.Buttons;

import Views.Utils.ButtonUtils.MenuButton;

import javax.swing.*;

/**
 * Created by Владимир on 31.10.2016.
 */
public final class ExitButton extends MenuButton
{
    public ExitButton(int fontSize, JFrame parent)
    {
        super("Exit", fontSize);
        addActionListener(e -> parent.dispose());
    }
}
