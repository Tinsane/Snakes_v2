package Controllers.MapEditorControllers;

import Controllers.MapEditorControllers.MapEditorAbstractController;
import Core.Game.GameCreatorWrapper;
import Views.MapEditorView.Frame;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ISmir on 06.11.2016.
 */
public class ResizeMapListener extends MapEditorAbstractController implements ActionListener
{
    public ResizeMapListener(GameCreatorWrapper gameCreator, Frame mapEditorFrame)
    {
        super(gameCreator, mapEditorFrame);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        throw new NotImplementedException();
    }
}
