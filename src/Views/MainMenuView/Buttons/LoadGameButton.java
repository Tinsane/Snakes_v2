package Views.MainMenuView.Buttons;

import Core.Game.Game;
import Views.Utils.FileUtils;
import Views.Utils.ButtonUtils.FrameLoaderButton;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by ISmir on 08.11.2016.
 */
public class LoadGameButton extends FrameLoaderButton
{
    public LoadGameButton(int fontSize, JFrame parent)
    {
        super("Load game", fontSize, parent);
    }

    @Override
    protected JFrame createFrame(JFrame parent)
    {
        Game game;
        game = FileUtils.LoadGameFromFileExceptionsHandled(parent);
        if (game == null)
            return null;
        return new Views.GameView.Frame((Views.MainMenuView.Frame) parent, game);
    }
}
