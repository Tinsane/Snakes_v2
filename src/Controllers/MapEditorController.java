package Controllers;

import Core.Game.GameCreator;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Владимир on 20.10.2016.
 */
public class MapEditorController implements KeyListener
{
    private GameCreator gameCreator;

    public MapEditorController(GameCreator gameCreator)
    {
        this.gameCreator = gameCreator;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {

    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}
