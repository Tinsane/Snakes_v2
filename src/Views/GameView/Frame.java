package Views.GameView;

import Controllers.GameController;
import Core.Game.Game;
import Views.Styles.Default.DefaultStyle;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by ISmir on 08.10.2016.
 */
public class Frame extends JFrame
{
    private Game game;
    private Timer updateTimer;
    private int currentTick;
    private Settings settings;
    Canvas canvas;

    public Frame(Game game) throws IOException
    {
        this(game, new Settings(new DefaultStyle()));
    }

    public Frame(Game game, Settings settings)
    {
        super();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize((game.getWidth() + 1) * settings.style.getTileSize(),
                (game.getHeight() + 1) * settings.style.getTileSize());
        setTitle("Snakes_v2");

        this.settings = settings;
        currentTick = settings.gameUpdateFrequency;

        this.game = game;

        updateTimer = new Timer(settings.updateInterval, x -> update());
        updateTimer.setRepeats(true);

        canvas = new Canvas(game, settings.style, false);
        add(canvas);
        addKeyListener(new GameController(game));
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

        if (currentTick == settings.gameUpdateFrequency)
        {
            game.update();
            currentTick = 0;
        }

        canvas.repaint((double)currentTick / settings.gameUpdateFrequency);
        ++currentTick;
    }
}
