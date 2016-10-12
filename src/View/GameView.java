package View;

import Core.Game.Game;
import Core.Game.GameCreator;
import View.Styles.DefaultStyle;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by ISmir on 08.10.2016.
 */
public class GameView extends JFrame
{
    private Game game;
    private Timer updateTimer;
    private GameViewSettings settings;
    GameCanvas canvas;

    public GameView() throws IOException
    {
        this(new GameViewSettings(new DefaultStyle()));
    }

    public GameView(GameViewSettings settings)
    {
        super();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(8 * settings.style.getTileSize(), 8 * settings.style.getTileSize());
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
            setVisible(false);
            dispose();
            return;
        }

        game.update();

        canvas.repaint();
    }
}
