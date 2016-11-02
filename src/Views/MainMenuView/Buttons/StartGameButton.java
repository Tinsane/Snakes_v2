package Views.MainMenuView.Buttons;

import Core.Game.GameCreator;
import Core.MapObjects.StaticMapObjects.Wall;
import Views.Utils.FrameLoaderButton;

import javax.swing.*;

/**
 * Created by Владимир on 31.10.2016.
 */
public final class StartGameButton extends FrameLoaderButton
{
    public StartGameButton(int fontSize, JFrame parent)
    {
        super("Start Game", fontSize, parent);
    }

    @Override
    protected JFrame createFrame(JFrame parent)
    {
        GameCreator creator = new GameCreator();
        creator.setMapSize(10, 10);
        creator.placeWall(5, 5);
        creator.placeMapObjectsInLineX(4, 1, 7, new Wall());
        return new Views.GameView.Frame((Views.MainMenuView.Frame) parent, creator.createGame(0, 0, 1));
    }
}
