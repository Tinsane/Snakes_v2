package Controllers.MapEditorControllers;

import Core.Game.GameCreators.GameCreatorWrapper;
import Views.MapEditorView.Frame;

/**
 * Created by ISmir on 06.11.2016.
 */
public class MapEditorAbstractController
{
    protected GameCreatorWrapper gameCreator;
    protected Frame mapEditorFrame;

    public MapEditorAbstractController(GameCreatorWrapper gameCreator, Frame mapEditorFrame)
    {
        this.gameCreator = gameCreator;
        this.mapEditorFrame = mapEditorFrame;
    }
}
