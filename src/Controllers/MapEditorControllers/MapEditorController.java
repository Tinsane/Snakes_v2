package Controllers.MapEditorControllers;

import Controllers.MovementBinds;
import Core.Game.GameCreators.GameCreatorWrapper;
import Core.Utils.VelocityVector;
import Views.MapEditorView.Frame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static Core.Game.GameCreators.GameCreatorWrapper.Pointer.MapObjectType;
import static Core.Game.GameCreators.GameCreatorWrapper.Pointer.MapPosition;

/**
 * Created by Владимир on 20.10.2016.
 */
public class MapEditorController extends MapEditorAbstractController implements KeyListener
{
    private final MovementBinds movementBinds;
    public MapEditorController(GameCreatorWrapper gameCreator, Frame mapEditorFrame)
    {
        super(gameCreator, mapEditorFrame);
        movementBinds = new MovementBinds(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT);
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        VelocityVector velocity = movementBinds.getDirection(e);
        if (velocity != null)
        {
            gameCreator.moveFocusedPositionOnVector(velocity);
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
        mapEditorFrame.update();
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}
