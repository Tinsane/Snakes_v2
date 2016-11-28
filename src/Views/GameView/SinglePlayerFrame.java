package Views.GameView;

import Controllers.GameController;
import Core.Game.Game;
import Core.GameObjects.Snake;
import Views.FinalScoreView.SinglePlayerFinalScoreFrame;

/**
 * Created by Владимир on 20.11.2016.
 */
public class SinglePlayerFrame extends Frame
{
    public SinglePlayerFrame(Views.MainMenuView.Frame mainMenuFrame, Game game, Settings settings)
    {
        super(mainMenuFrame, game, settings);
        restartGame();
    }

    @Override
    protected void onGameFinished()
    {
        super.onGameFinished();
        new SinglePlayerFinalScoreFrame(mainMenuFrame, this::restartGame, Snake.getSnake(game).getLength());
    }

    @Override
    protected void restartGame()
    {
        super.restartGame();
        clear();
        canvas = new SinglePlayerGameCanvas(this.game, settings.singlePlayerStyle, false);
        add(canvas);
        addKeyListener(new GameController(this.game, 0, settings.movementControls.get(Settings.ARROWS_CONTROL)));
        setVisible(true);
        pack();
    }
}
