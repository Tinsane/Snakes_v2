package View;

import Controller.ArrowsKeyListener;
import Core.Game.Game;
import View.Styles.Default.DefaultStyle;

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

    public GameView(Game game) throws IOException
    {
        this(game, new GameViewSettings(new DefaultStyle()));
    }

    public GameView(Game game, GameViewSettings settings)
    {
        super();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize((game.getWidth() + 1) * settings.style.getTileSize(),
                (game.getHeight() + 1) * settings.style.getTileSize());
        setTitle("Snakes_v2");

        this.settings = settings;

        this.game = game;

        updateTimer = new Timer(settings.updateInterval, x -> update());
        updateTimer.setRepeats(true);

        canvas = new GameCanvas(game, settings.style, false);
        add(canvas);
        addKeyListener(new ArrowsKeyListener(game));
        setVisible(true);
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
