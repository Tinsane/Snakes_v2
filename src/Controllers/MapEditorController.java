package Controllers;

import Core.Game.GameCreator;
import Core.Game.GameCreatorWrapper;
import Core.Utils.VelocityVector;
import Views.MapEditorView.Frame;
import Views.MapEditorView.MapEditorCanvas;

import javax.lang.model.type.ErrorType;
import javax.lang.model.type.UnknownTypeException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static Core.Game.GameCreatorWrapper.Pointer.MapObjectType;
import static Core.Game.GameCreatorWrapper.Pointer.MapPosition;

/**
 * Created by Владимир on 20.10.2016.
 */
public class MapEditorController implements KeyListener
{
    private GameCreatorWrapper gameCreator;
    private Frame frame;

    public MapEditorController(GameCreatorWrapper gameCreator, Frame frame)
    {
        this.gameCreator = gameCreator;
        this.frame = frame;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        VelocityVector velocity = Utils.getDirection(e);
        if (velocity != null)
        {
            if (gameCreator.pointer == MapObjectType)
            {
                if (velocity.x == 0)
                    gameCreator.moveMapObjectIndexOrStay(velocity.y);
            }
            else
                gameCreator.movePositionOrStay(velocity);
        }
        else
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_1:
                    gameCreator.pointer = MapPosition;
                    break;
                case KeyEvent.VK_2:
                    gameCreator.pointer = MapObjectType;
                    break;
                case KeyEvent.VK_SPACE:
                    gameCreator.placeMapObject();
            }
        }
        frame.update();
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}
