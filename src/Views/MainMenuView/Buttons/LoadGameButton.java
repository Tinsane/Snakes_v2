package Views.MainMenuView.Buttons;

import Core.Game.Game;
import Views.GameView.MultiplayerFrame;
import Views.GameView.SinglePlayerFrame;
import Views.Utils.FileUtils;
import Views.Utils.ButtonUtils.FrameLoaderButton;

import javax.swing.*;

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
        return new MultiplayerFrame((Views.MainMenuView.Frame) parent, game);
    }
}
