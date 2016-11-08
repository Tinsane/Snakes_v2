package Controllers.MapEditorControllers.ActionListeners;

import Core.Game.GameCreatorWrapper;
import Views.MapEditorView.Frame;

import java.awt.event.ActionEvent;

/**
 * Created by ISmir on 06.11.2016.
 */
public class PlaceSnakeListener extends MapEditorAbstractActionListener
{
    public PlaceSnakeListener(GameCreatorWrapper gameCreator, Frame mapEditorFrame)
    {
        super(gameCreator, mapEditorFrame);
    }

    @Override
    public void mapEditorActionPerformed()
    {
        gameCreator.placeSnake(1);
    }
}
