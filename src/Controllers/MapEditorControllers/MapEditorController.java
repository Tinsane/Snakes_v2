package Controllers.MapEditorControllers;

import Controllers.MapEditorControllers.MapEditorAbstractController;
import Controllers.Utils;
import Core.Game.GameCreatorWrapper;
import Core.Utils.VelocityVector;
import Views.MapEditorView.Frame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static Core.Game.GameCreatorWrapper.Pointer.MapObjectType;
import static Core.Game.GameCreatorWrapper.Pointer.MapPosition;

/**
 * Created by Владимир on 20.10.2016.
 */
public class MapEditorController extends MapEditorAbstractController implements KeyListener
{
    public MapEditorController(GameCreatorWrapper gameCreator, Frame mapEditorFrame)
    {
        super(gameCreator, mapEditorFrame);
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
                if (velocity.y == 0)
                    gameCreator.moveMapObjectIndexOrStay(velocity.x);
            }
            else
                gameCreator.movePositionWithResizing(velocity);
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
