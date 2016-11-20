package Views.GameView;

import Controllers.GameController;
import Core.Game.Game;
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
        addWindowListener(new ParentFrameRestorer(this, mainMenuFrame));
        canvas = new GameCanvas(game, settings.style, false);
        add(canvas);
        pack();
        setVisible(true);
        for(int i = 0; i < game.getSnakes().size(); ++i)
            addKeyListener(new GameController(game, i, settings.movementBinds.get(i)));
        start();
    }

    @Override
    protected void onGameFinished()
    {
        super.onGameFinished();
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
