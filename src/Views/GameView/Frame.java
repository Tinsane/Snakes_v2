package Views.GameView;

import Controllers.GameController;
import Core.Game.Game;
import Views.Utils.GameCopyUtils;

import javax.swing.*;
import java.awt.event.KeyListener;
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
    private Game initialGame;
    protected Settings settings;

    public Frame(Views.MainMenuView.Frame mainMenuFrame, Game game)
    {
        super();
        this.mainMenuFrame = mainMenuFrame;
        settings = mainMenuFrame.settings;
        initialGame = game;
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                updateTimer.stop();
            }
        });

        setTitle("Snakes_v2");

        updateTimer = new Timer(settings.updateInterval, x -> update());
        updateTimer.setRepeats(true);
    }

    protected void clear()
    {
        if (canvas != null)
            remove(canvas);
        KeyListener[] listeners = getKeyListeners();
        for (KeyListener listener : listeners)
            removeKeyListener(listener);
    }

    protected void restartGame()
    {
        game = GameCopyUtils.CopyGame(initialGame);
        currentTick = settings.gameUpdateFrequency;
        updateTimer.restart();
    }

    protected void onGameFinished()
    {
        updateTimer.stop();
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
