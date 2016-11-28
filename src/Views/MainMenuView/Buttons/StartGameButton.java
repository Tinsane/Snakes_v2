package Views.MainMenuView.Buttons;

import Core.Game.GameCreators.GameCreator;
import Core.MapObjects.StaticMapObjects.Wall;
import Views.GameView.Settings;
import Views.GameView.SinglePlayerFrame;
import Views.Utils.ButtonUtils.FrameLoaderButton;

import javax.swing.*;

/**
 * Created by Владимир on 31.10.2016.
 */
public final class StartGameButton extends FrameLoaderButton
{
    private final Settings settings;
    public StartGameButton(int fontSize, JFrame parent, Settings settings)
    {
        super("Start Game", fontSize, parent);
        this.settings = settings;
    }

    @Override
    protected JFrame createFrame(JFrame parent)
    {
        GameCreator creator = new GameCreator(settings.gameUpdatingSystem);
        creator.setMapSize(10, 10);
        creator.placeWall(5, 5);
        creator.placeMapObjectsInLineX(4, 1, 7, new Wall());
        creator.placeSnake(0, 0, 1);
        return new SinglePlayerFrame((Views.MainMenuView.Frame) parent, creator.createGame(), settings);
    }
}
