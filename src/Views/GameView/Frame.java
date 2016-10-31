package Views.GameView;

import Controllers.GameController;
import Core.Game.Game;
import Views.Utils.ParentFrameRestorer;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by ISmir on 08.10.2016.
 */
public class Frame extends JFrame
{
    private final Views.MainMenuView.Frame mainMenuFrame;
    private Game game;
    private final Timer updateTimer;
    private int currentTick;
    private Settings settings;
    private final GameViewCanvas canvas;

    public Frame(Views.MainMenuView.Frame mainMenuFrame, Game game)
    {
        super();
        this.mainMenuFrame = mainMenuFrame;
        settings = mainMenuFrame.settings;
        this.game = game;
        setSize((game.getWidth() + 1) * settings.style.getTileSize(),
                (game.getHeight() + 1) * settings.style.getTileSize());
        addWindowListener(new WindowAdapter()
        {
            //TODO: code review by Van
            //TODO: Убрать в отдельный класс. Тут это лишние 7 строчек, которые мусолят глаза.
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

        canvas = new GameViewCanvas(game, settings.style, false);
        add(canvas);
        addKeyListener(new GameController(game));
        setVisible(true);
        start();
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
