package Controllers.MapEditorControllers;

import Core.Game.GameCreatorWrapper;
import Views.MapEditorView.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ISmir on 06.11.2016.
 */
public class SaveMapListener extends MapEditorAbstractController implements ActionListener
{
    public SaveMapListener(GameCreatorWrapper gameCreator, Frame mapEditorFrame)
    {
        super(gameCreator, mapEditorFrame);
    }

    @Override
    public void actionPerformed(ActionEvent e) throws IllegalStateException
    {
        if (gameCreator.getSnake() == null)
            throw new IllegalStateException("Snake not set");
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(mapEditorFrame) == JFileChooser.APPROVE_OPTION)
        {
            throw new IWantToSleepException();
        }
    }

    private class IWantToSleepException extends RuntimeException
    {
    }
}
