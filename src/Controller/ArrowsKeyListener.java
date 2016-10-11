package Controller;

import Core.Game.Game;
import Core.GameCommands.ChangeSnakeVelocityCommand;
import Core.GameCommands.GameCommand;
import Core.Utils.VelocityVector;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by ISmir on 11.10.2016.
 */
public class ArrowsKeyListener implements KeyListener
{
    private Game game;

    public ArrowsKeyListener(Game game)
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
        VelocityVector newSnakeVelocity;
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_RIGHT:
                newSnakeVelocity = VelocityVector.right;
                break;
            case KeyEvent.VK_LEFT:
                newSnakeVelocity = VelocityVector.left;
                break;
            case KeyEvent.VK_DOWN:
                newSnakeVelocity = VelocityVector.down;
                break;
            case KeyEvent.VK_UP:
                newSnakeVelocity = VelocityVector.up;
                break;
            default:
                return;
        }
        game.executeCommand(new ChangeSnakeVelocityCommand(newSnakeVelocity));
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}
