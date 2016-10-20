package View;

import Core.Game.GameCreator;
import Core.MapObjects.StaticMapObjects.Wall;
import View.Styles.Default.DefaultStyle;

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
            GameView view = new GameView(creator.createGame(0, 0, 1), new GameViewSettings(new DefaultStyle()));
            view.start();
        } catch (IOException e)
        {
            // TODO: logging
        }
    }
}
