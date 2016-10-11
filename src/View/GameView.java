package View;

import Core.Game.Game;
import Core.Game.GameCreator;

import javax.swing.*;

/**
 * Created by ISmir on 08.10.2016.
 */
public class GameView extends JFrame
{
    private Game game;
    private Timer updateTimer;
    private GameViewSettings settings;
    GameCanvas canvas;

    public GameView()
    {
        this(new GameViewSettings());
    }

    public GameView(GameViewSettings settings)
    {
        super();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400, 400);
        setTitle("Snakes_v2");

        this.settings = settings;

        GameCreator creator = new GameCreator();
        creator.setMapSize(5, 5);
        game = creator.createGame(0, 0, 1); // need to use saved map here

        updateTimer = new Timer(settings.updateInterval, x -> update());
        updateTimer.setRepeats(true);

        canvas = new GameCanvas(game, settings.style, false);
        add(canvas);
    }

    public void start()
    {
        updateTimer.start();
    }

    public void stop()
    {
        updateTimer.stop();
    }

    private void update()
    {
        if (game.isFinished())
        {
            stop();
            return;
        }

        game.update();

        canvas.repaint();
    }
}
