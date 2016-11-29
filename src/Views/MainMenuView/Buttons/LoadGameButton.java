package Views.MainMenuView.Buttons;

import Core.Game.Game;
import Core.GameObjects.Snake;
import Views.GameView.MultiplayerFrame;
import Views.GameView.Settings;
import Views.GameView.SinglePlayerFrame;
import Views.Utils.FileUtils;
import Views.Utils.ButtonUtils.FrameLoaderButton;

import javax.swing.*;

/**
 * Created by ISmir on 08.11.2016.
 */
public class LoadGameButton extends FrameLoaderButton
{
    private Settings settings;
    public LoadGameButton(int fontSize, JFrame parent, Settings settings)
    {
        super("Load game", fontSize, parent);
        this.settings = settings;
    }

    @Override
    protected JFrame createFrame(JFrame parent)
    {
        Game game;
        game = FileUtils.LoadGameFromFileExceptionsHandled(parent);
        if (game == null)
            return null;

        if (game.getGameObjects().stream().filter(gameObject -> gameObject instanceof Snake).count() == 1)
            return new SinglePlayerFrame((Views.MainMenuView.Frame) parent, game, settings);
        return new MultiplayerFrame((Views.MainMenuView.Frame) parent, game, settings);
    }
}
