package Views.GameView;

import Controllers.GameController;
import Core.Game.Game;
import Views.MainMenuView.MainMenuRestorer;
import Views.Styles.Default.DefaultStyle;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by ISmir on 08.10.2016.
 */
public class Frame extends JFrame
{
    private final Views.MainMenuView.Frame mainMenuFrame;
    private Game game;
    private Timer updateTimer;
    private int currentTick;
    private Settings settings;
    GameViewCanvas canvas;

    public Frame(Views.MainMenuView.Frame mainMenuFrame, Game game) throws IOException
    {
        this(mainMenuFrame, game, new Settings(new DefaultStyle()));
    }

    public Frame(Views.MainMenuView.Frame mainMenuFrame, Game game, Settings settings)
    {
        super();
        this.mainMenuFrame = mainMenuFrame;
        setSize((game.getWidth() + 1) * settings.style.getTileSize(),
                (game.getHeight() + 1) * settings.style.getTileSize());
        addWindowListener(new MainMenuRestorer(this, mainMenuFrame));
        setTitle("Snakes_v2");

        this.settings = settings;
        currentTick = settings.gameUpdateFrequency;

        this.game = game;

        updateTimer = new Timer(settings.updateInterval, x -> update());
        updateTimer.setRepeats(true);

        canvas = new GameViewCanvas(game, settings.style, false);
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
            new Views.FinalScoreView.Frame(mainMenuFrame, game.getSnake().getLength());
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
