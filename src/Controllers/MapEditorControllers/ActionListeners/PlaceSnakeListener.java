package Controllers.MapEditorControllers.ActionListeners;

import Core.Game.GameCreators.GameCreatorWrapper;
import Views.MapEditorView.Frame;

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
        gameCreator.placeSnake();
    }
}
