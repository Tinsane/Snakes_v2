package Controllers.MapEditorControllers.ActionListeners;

import Controllers.MapEditorControllers.MapEditorAbstractController;
import Core.Game.GameCreators.GameCreatorWrapper;
import Views.MapEditorView.Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ISmir on 08.11.2016.
 */
public abstract class MapEditorAbstractActionListener extends MapEditorAbstractController implements ActionListener
{
    public MapEditorAbstractActionListener(GameCreatorWrapper gameCreator, Frame mapEditorFrame)
    {
        super(gameCreator, mapEditorFrame);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        mapEditorActionPerformed();
        mapEditorFrame.update();
    }

    public abstract void mapEditorActionPerformed();
}
