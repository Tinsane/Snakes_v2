package MainPackage;

import Core.Game.GameCreator;
import Core.MapObjects.StaticMapObjects.Wall;
import Views.GameView.Frame;
import Views.GameView.Settings;
import Views.Styles.Default.DefaultStyle;

import java.io.IOException;

/**
 * Created by ISmir on 08.10.2016.
 */
public class Main
{
    public static void main(String[] args)
    {
        try
        {
            GameCreator creator = new GameCreator();
            creator.setMapSize(10, 10);
            creator.placeWall(5, 5);
            creator.placeMapObjectsInLineX(4, 1, 7, new Wall());
            Frame gameFrame = new Frame(creator.createGame(0, 0, 1), new Settings(new DefaultStyle()));
            gameFrame.start();
        } catch (IOException e)
        {
            // TODO: logging
        }
    }
}
