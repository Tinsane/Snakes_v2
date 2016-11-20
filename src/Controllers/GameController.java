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
    private final Game game;
    private final int snakeIndex;
    private final MovementBinds binds;

    public GameController(Game game, int snakeIndex, MovementBinds binds)
    {
        this.game = game;
        this.snakeIndex = snakeIndex;
        this.binds = binds;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        VelocityVector newSnakeVelocity = binds.getDirection(e);
        if (newSnakeVelocity != null)
            game.executeCommand(new ChangeSnakeVelocityCommand(snakeIndex, newSnakeVelocity));
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}
