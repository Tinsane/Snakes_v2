package Views.FileUtils;

import Core.Game.Game;

import javax.swing.*;
import java.io.*;

/**
 * Created by ISmir on 08.11.2016.
 */
public class FileUtils
{
    public static File ChooseFile(JFrame frame)
    {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION)
            return fileChooser.getSelectedFile();
        return null;
    }

    public static Game LoadGameFromFile(JFrame frame) throws IOException, ClassNotFoundException
    {
        File file = ChooseFile(frame);
        if (file == null)
            return null;
        try(FileInputStream inputStream = new FileInputStream(file))
        {
            return Game.loadGame(inputStream);
        }
    }

    public static void SaveGameToFile(JFrame frame, Game game) throws IOException, ClassNotFoundException
    {
        File file = ChooseFile(frame);
        if (file != null)
            try(FileOutputStream outputStream = new FileOutputStream(file))
            {
                game.writeGame(outputStream);
            }
    }
}
