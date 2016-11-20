package Views.GameView;

import Controllers.GameController;
import Core.Game.Game;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by ISmir on 08.10.2016.
 */
public abstract class Frame extends JFrame
{
    protected final Views.MainMenuView.Frame mainMenuFrame;
    protected GameCanvas canvas;
    protected Game game;
    private final Timer updateTimer;
    private int currentTick;
    protected Settings settings;

    public Frame(Views.MainMenuView.Frame mainMenuFrame, Game game)
    {
        super();
        this.mainMenuFrame = mainMenuFrame;
        settings = mainMenuFrame.settings;
        this.game = game;
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                 stop();
            }
        });

        setTitle("Snakes_v2");

        currentTick = settings.gameUpdateFrequency;

        updateTimer = new Timer(settings.updateInterval, x -> update());
        updateTimer.setRepeats(true);
    }

    public void start()
    {
        updateTimer.start();
    }

    public void stop()
    {
        updateTimer.stop();
    }

    protected void onGameFinished()
    {
        stop();
        setVisible(false);
        dispose();
    }

    private void update()
    {
        if (game.isFinished())
        {
            onGameFinished();
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
