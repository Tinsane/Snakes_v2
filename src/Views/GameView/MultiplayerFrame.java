package Views.GameView;

import Controllers.GameController;
import Core.Game.Game;
import Views.FinalScoreView.MultiplayerFinalScoreFrame;

/**
 * Created by Владимир on 20.11.2016.
 */
public class MultiplayerFrame extends Frame
{
    public MultiplayerFrame(Views.MainMenuView.Frame mainMenuFrame, Game game, Settings settings)
    {
        super(mainMenuFrame, game, settings);
        restartGame();
    }

    @Override
    protected void onGameFinished()
    {
        super.onGameFinished();
        new MultiplayerFinalScoreFrame(mainMenuFrame, this::restartGame, game.getAliveSnakeIndex());
    }

    @Override
    protected void restartGame()
    {
        super.restartGame();
        clear();
        canvas = new GameCanvas(this.game, settings.multiplayerStyle, false);
        add(canvas);
        for (int i = 0; i < this.game.getSnakes().size(); ++i) // TODO: create two players frame
            addKeyListener(new GameController(this.game, i, settings.movementControls.get(i)));
        setVisible(true);
        pack();
    }
}
