package Views.MainMenuView.Buttons;

import Views.Utils.FrameLoaderButton;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by Владимир on 31.10.2016.
 */
public final class MapEditorButton extends FrameLoaderButton
{
    public MapEditorButton(int fontSize, JFrame parent)
    {
        super("Map Editor", fontSize, parent);
    }

    @Override
    protected JFrame createFrame(JFrame parent)
    {
        try
        {
            return new Views.MapEditorView.Frame();
        } catch (IOException e)
        {
            throw new NotImplementedException(); // TODO: map editor didn't load
        }
    }
}
