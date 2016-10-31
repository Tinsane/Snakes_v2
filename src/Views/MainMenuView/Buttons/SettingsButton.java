package Views.MainMenuView.Buttons;

import Views.Utils.FrameLoaderButton;

import javax.swing.*;

/**
 * Created by Владимир on 31.10.2016.
 */
public class SettingsButton extends FrameLoaderButton
{
    public SettingsButton(String text, int fontSize, JFrame parent)
    {
        super(text, fontSize, parent);
    }

    @Override
    protected JFrame createFrame(JFrame parent)
    {
        return new Views.SettingsView.Frame((Views.MainMenuView.Frame) parent);
    }
}
