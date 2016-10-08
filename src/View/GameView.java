package View;

import Core.Game.Game;
import Core.Game.GameCreator;
import View.Styles.GameStyle;

import javax.swing.*;

/**
 * Created by ISmir on 08.10.2016.
 */
public class GameView extends JFrame
{
    public int DEFAULT_UPDATE_INTERVAL = 300;
    private Game game;
    private GameStyle style;
    private Timer updateTimer;

    public GameView(int updateInterval, GameStyle style)
    {
        super();

        GameCreator creator = new GameCreator();
        creator.setMapSize(5, 5);
        game = creator.createGame(0, 0, 1); // need to use saved map here

        updateTimer = new Timer(updateInterval, x -> paint());
        updateTimer.setRepeats(true);

        this.style = style;
    }

    public void start()
    {
        updateTimer.start();
    }

    public void stop()
    {
        updateTimer.stop();
    }

    public void paint()
    {
        game.update();

        Drawable[][] map = game.getCurrentMap();
        // now need to paint it somehow
    }
}
