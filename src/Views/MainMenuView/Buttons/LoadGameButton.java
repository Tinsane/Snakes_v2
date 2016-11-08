package Views.MainMenuView.Buttons;

import Core.Game.Game;
import Core.Game.GameCreator;
import Core.MapObjects.StaticMapObjects.Wall;
import Views.FileUtils.FileUtils;
import Views.Utils.FrameLoaderButton;
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
        try
        {
            game = FileUtils.LoadGameFromFile(parent);
        } catch (IOException | ClassNotFoundException e)
        {
            throw new NotImplementedException(); //TODO: game wasn't loaded
        }
        return new Views.GameView.Frame((Views.MainMenuView.Frame) parent, game);
    }
}
