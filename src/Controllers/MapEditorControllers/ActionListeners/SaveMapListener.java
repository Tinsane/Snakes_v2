package Controllers.MapEditorControllers.ActionListeners;

import Core.Game.GameCreatorWrapper;
import Views.Utils.FileUtils;
import Views.MapEditorView.Frame;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.event.ActionEvent;
import java.io.*;

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
        if (gameCreator.getSnake() == null)
            throw new IllegalStateException("Snake not set"); // TODO: show window
        FileUtils.SaveGameToFileExceptionsHandled(mapEditorFrame, gameCreator.createGame());
    }
}
