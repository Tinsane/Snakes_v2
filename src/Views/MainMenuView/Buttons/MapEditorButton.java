package Views.MainMenuView.Buttons;

import Views.GameView.Settings;
import Views.Utils.ButtonUtils.FrameLoaderButton;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by Владимир on 31.10.2016.
 */
public final class MapEditorButton extends FrameLoaderButton
{
    private final Settings settings;

    public MapEditorButton(int fontSize, JFrame parent, Settings settings)
    {
        super("Map Editor", fontSize, parent);
        this.settings = settings;
    }

    @Override
    protected JFrame createFrame(JFrame parent)
    {
        try
        {
            return new Views.MapEditorView.Frame(settings);
        } catch (IOException e)
        {
            throw new NotImplementedException(); // TODO: map editor didn't load
        }
    }
}
