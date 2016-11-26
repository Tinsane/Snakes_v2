package Controllers.MapEditorControllers.ActionListeners;

import Core.Game.Game;
import Core.Game.GameCreators.GameCreatorWrapper;
import Views.MapEditorView.Frame;

import static Views.Utils.FileUtils.LoadGameFromFileExceptionsHandled;

/**
 * Created by ISmir on 08.11.2016.
 */
public class LoadMapListener extends MapEditorAbstractActionListener
{
    public LoadMapListener(GameCreatorWrapper gameCreator, Frame mapEditorFrame)
    {
        super(gameCreator, mapEditorFrame);
    }
    @Override
    public void mapEditorActionPerformed()
    {
        Game game = LoadGameFromFileExceptionsHandled(mapEditorFrame);
        gameCreator.Populate(game);
    }
}
