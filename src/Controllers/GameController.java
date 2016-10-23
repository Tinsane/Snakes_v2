package Controllers;

import Core.Game.Game;
import Core.GameCommands.ChangeSnakeVelocityCommand;
import Core.Utils.VelocityVector;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by ISmir on 11.10.2016.
 */
public class GameController implements KeyListener
{
    private Game game;

    public GameController(Game game)
    {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        VelocityVector newSnakeVelocity = Utils.getDirection(e);
        if (newSnakeVelocity != null)
            game.executeCommand(new ChangeSnakeVelocityCommand(newSnakeVelocity));
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}
