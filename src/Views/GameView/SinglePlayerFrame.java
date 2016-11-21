package Views.GameView;

import Controllers.GameController;
import Core.Game.Game;
import Views.FinalScoreView.SinglePlayerFinalScoreFrame;

/**
 * Created by Владимир on 20.11.2016.
 */
public class SinglePlayerFrame extends Frame
{
    public SinglePlayerFrame(Views.MainMenuView.Frame mainMenuFrame, Game game)
    {
        super(mainMenuFrame, game);
        restartGame();
    }

    @Override
    protected void onGameFinished()
    {
        super.onGameFinished();
        new SinglePlayerFinalScoreFrame(mainMenuFrame, this::restartGame, game.getSnakes().get(0).getLength());
    }

    @Override
    protected void restartGame()
    {
        super.restartGame();
        clear();
        canvas = new SinglePlayerGameCanvas(this.game, settings.style, false);
        add(canvas);
        addKeyListener(new GameController(this.game, 0, settings.movementControls.get(Settings.ARROWS_CONTROL)));
        setVisible(true);
        pack();
    }
}
