package Views.GameView;

import Controllers.GameController;
import Core.Game.Game;

/**
 * Created by Владимир on 20.11.2016.
 */
public class SinglePlayerFrame extends Frame
{
    public SinglePlayerFrame(Views.MainMenuView.Frame mainMenuFrame, Game game)
    {
        super(mainMenuFrame, game);
        canvas = new SinglePlayerGameCanvas(game, settings.style, false);
        add(canvas);
        pack();
        setVisible(true);
        addKeyListener(new GameController(game, 0, settings.movementBinds.get(0)));
        start();
    }

    @Override
    protected void onGameFinished()
    {
        super.onGameFinished();
        new Views.FinalScoreView.Frame(mainMenuFrame, game.getSnakes().get(0).getLength());
    }
}
