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
    private Game game;
    private Timer updateTimer;
    private JLabel[][] field;
    private GameViewSettings settings;

    public GameView()
    {
        super();
        init(new GameViewSettings());
    }

    public GameView(GameViewSettings settings)
    {
        super();
        init(settings);
    }

    public void init(GameViewSettings settings)
    {
        this.settings = settings;

        GameCreator creator = new GameCreator();
        creator.setMapSize(5, 5);
        game = creator.createGame(0, 0, 1); // need to use saved map here

        updateTimer = new Timer(settings.updateInterval, x -> paint());
        updateTimer.setRepeats(true);

        for (int i = 0; i < game.getWidth(); ++i)
            for (int j = 0; j < game.getHeight(); ++j)
                field[i][j] = new JLabel();
    }

    public void start()
    {
        updateTimer.start();
    }

    public void stop()
    {
        updateTimer.stop();
    }

    private void paint()
    {
        game.update();

        Drawable[][] map = game.getCurrentMap();
        for (int i = 0; i < field.length; ++i)
            for (int j = 0; j < field[0].length; ++j)
                field[i][j].setIcon(map[i][j].getIcon(settings.style, game));
    }
}
