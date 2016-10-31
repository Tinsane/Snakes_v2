package Views.MainMenuView.Buttons;

import Views.Utils.FrameLoaderButton;

import javax.swing.*;

/**
 * Created by Владимир on 31.10.2016.
 */
public class MapEditorButton extends FrameLoaderButton
{
    public MapEditorButton(String text, int fontSize, JFrame parent)
    {
        super(text, fontSize, parent);
    }

    @Override
    protected JFrame createFrame(JFrame parent)
    {
        Views.MainMenuView.Frame mainMenu = (Views.MainMenuView.Frame) parent;
        return new Views.MapEditorView.Frame(mainMenu.settings.style);
    }
}
