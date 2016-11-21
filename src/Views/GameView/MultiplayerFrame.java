package Views.GameView;

import Controllers.GameController;
import Core.Game.Game;
import Views.FinalScoreView.MultiplayerFinalScoreFrame;
import Views.Utils.ParentFrameRestorer;

import java.awt.event.WindowEvent;

/**
 * Created by Владимир on 20.11.2016.
 */
public class MultiplayerFrame extends Frame
{
    public MultiplayerFrame(Views.MainMenuView.Frame mainMenuFrame, Game game)
    {
        super(mainMenuFrame, game);
        restartGame();
    }

    @Override
    protected void onGameFinished()
    {
        super.onGameFinished();
        new MultiplayerFinalScoreFrame(mainMenuFrame, this::restartGame, "Player " + (game.getAliveSnakeIndex() + 1));
    }

    @Override
    protected void restartGame()
    {
        super.restartGame();
        clear();
        canvas = new GameCanvas(this.game, settings.style, false);
        add(canvas);
        for(int i = 0; i < this.game.getSnakes().size(); ++i) // TODO: create two players frame
            addKeyListener(new GameController(this.game, i, settings.movementControls.get(i)));
        setVisible(true);
        pack();
    }
}
