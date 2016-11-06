package Views.MapEditorView;

import Controllers.MapEditorControllers.AddSnakeListener;
import Controllers.MapEditorControllers.ResizeMapListener;
import Controllers.MapEditorControllers.SaveMapListener;
import Core.Game.GameCreatorWrapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by ISmir on 06.11.2016.
 */
public class MapEditorMenuBar extends JMenuBar
{
    public MapEditorMenuBar(GameCreatorWrapper gameCreator, Frame mapEditorFrame)
    {
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveItem.addActionListener(new SaveMapListener(gameCreator, mapEditorFrame));
        fileMenu.add(saveItem);

        JMenu editMenu = new JMenu("Edit");
        JMenuItem resizeItem = new JMenuItem("Change map size");
        resizeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        resizeItem.addActionListener(new ResizeMapListener(gameCreator, mapEditorFrame));
        editMenu.add(resizeItem);

        JMenuItem addSnakeItem = new JMenuItem("Add snake");
        resizeItem.addActionListener(new AddSnakeListener(gameCreator, mapEditorFrame));
        editMenu.add(addSnakeItem);

        add(fileMenu);
        add(editMenu);
    }
}
