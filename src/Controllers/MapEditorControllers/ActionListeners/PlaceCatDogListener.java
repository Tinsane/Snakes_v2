package Controllers.MapEditorControllers.ActionListeners;

import Core.Game.GameCreators.GameCreatorWrapper;
import Views.MapEditorView.Frame;

/**
 * Created by ISmir on 28.11.2016.
 */
public class PlaceCatDogListener extends MapEditorAbstractActionListener
{
    public PlaceCatDogListener(GameCreatorWrapper gameCreator, Frame mapEditorFrame)
    {
        super(gameCreator, mapEditorFrame);
    }

    @Override
    public void mapEditorActionPerformed()
    {
        gameCreator.placeCatDog();
    }
}
