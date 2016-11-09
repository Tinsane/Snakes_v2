package Views.MapEditorView;

import Controllers.MapEditorControllers.ActionListeners.LoadMapListener;
import Controllers.MapEditorControllers.ActionListeners.PlaceSnakeListener;
import Controllers.MapEditorControllers.ActionListeners.SaveMapListener;
import Core.Game.GameCreatorWrapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by ISmir on 06.11.2016.
 */
public class MapEditorMenuBar extends JMenuBar
{
    // TODO: DRY
    public MapEditorMenuBar(GameCreatorWrapper gameCreator, Frame mapEditorFrame)
    {
        JMenu fileMenu = new JMenu("File");

        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveItem.addActionListener(new SaveMapListener(gameCreator, mapEditorFrame));
        fileMenu.add(saveItem);

        JMenuItem openItem = new JMenuItem("Open");
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        openItem.addActionListener(new LoadMapListener(gameCreator, mapEditorFrame));
        fileMenu.add(openItem);

        JMenu editMenu = new JMenu("Edit");

        JMenuItem addSnakeItem = new JMenuItem("Place snake");
        addSnakeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, ActionEvent.CTRL_MASK));
        addSnakeItem.addActionListener(new PlaceSnakeListener(gameCreator, mapEditorFrame));
        editMenu.add(addSnakeItem);

        add(fileMenu);
        add(editMenu);
    }
}
