package Controllers.MapEditorControllers;

import Core.Game.GameCreatorWrapper;
import Views.MapEditorView.Frame;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

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
    public void actionPerformed(ActionEvent e)
    {
        if (gameCreator.getSnake() == null)
            throw new IllegalStateException("Snake not set"); // TODO: show window
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(mapEditorFrame) == JFileChooser.APPROVE_OPTION)
        {
            File file = fileChooser.getSelectedFile();
            try
            {
                try(FileOutputStream outputStream = new FileOutputStream(file))
                {
                    gameCreator.createGame().writeGame(outputStream);
                }
            } catch (IOException | ClassNotFoundException e1)
            {
                throw new NotImplementedException();
                //TODO: write to log, show window
            }
        }
    }
}
