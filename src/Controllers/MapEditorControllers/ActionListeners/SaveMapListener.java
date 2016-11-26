package Controllers.MapEditorControllers.ActionListeners;

import Core.Game.GameCreators.GameCreatorWrapper;
import Views.Utils.FileUtils;
import Views.MapEditorView.Frame;

/**
 * Created by ISmir on 06.11.2016.
 */
public class SaveMapListener extends MapEditorAbstractActionListener
{
    public SaveMapListener(GameCreatorWrapper gameCreator, Frame mapEditorFrame)
    {
        super(gameCreator, mapEditorFrame);
    }

    @Override
    public void mapEditorActionPerformed()
    {
        if (gameCreator.getGameObjects() == null)
            throw new IllegalStateException("Snake not set"); // TODO: show window
        FileUtils.SaveGameToFileExceptionsHandled(mapEditorFrame, gameCreator.createGame());
    }
}
