package Views.MapEditorView;

import Controllers.MapEditorControllers.ActionListeners.LoadMapListener;
import Controllers.MapEditorControllers.ActionListeners.PlaceSnakeListener;
import Controllers.MapEditorControllers.ActionListeners.SaveMapListener;
import Core.Game.GameCreatorWrapper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * Created by ISmir on 06.11.2016.
 */
public class MapEditorMenuBar extends JMenuBar
{
    private final String FILE = "File";
    private final String EDIT = "Edit";
    private final String SAVE = "Save";
    private final String OPEN = "Open";
    private final String PLACE_SNAKE = "Place snakes";

    HashMap<String, JMenu> menus = new HashMap<>();

    private void addMenu(String menuName)
    {
        JMenu menu = new JMenu(menuName);
        menus.put(menuName, menu);
        add(menu);
    }

    private void addMenuItem(String menuName, JMenuItem menuItem)
    {
        menus.get(menuName).add(menuItem);
    }

    private void addMenuItem(String menuName, String itemName, KeyStroke keyStroke, ActionListener actionListener)
    {
        JMenuItem item = new JMenuItem(itemName);
        item.setAccelerator(keyStroke);
        item.addActionListener(actionListener);
        addMenuItem(menuName, item);
    }

    private void addFileMenuItem(String itemName, KeyStroke keyStroke, ActionListener actionListener)
    {
        addMenuItem(FILE, itemName, keyStroke, actionListener);
    }

    private void addEditMenuItem(String itemName, KeyStroke keyStroke, ActionListener actionListener)
    {
        addMenuItem(EDIT, itemName, keyStroke, actionListener);
    }

    private KeyStroke WithCtrl(int keyCode)
    {
        return KeyStroke.getKeyStroke(keyCode, ActionEvent.CTRL_MASK);
    }

    // TODO: DRY
    public MapEditorMenuBar(GameCreatorWrapper gameCreator, Frame mapEditorFrame)
    {
        addMenu(FILE);
        addMenu(EDIT);

        addFileMenuItem(SAVE, WithCtrl(KeyEvent.VK_S), new SaveMapListener(gameCreator, mapEditorFrame));
        addFileMenuItem(OPEN, WithCtrl(KeyEvent.VK_O), new LoadMapListener(gameCreator, mapEditorFrame));
        addEditMenuItem(PLACE_SNAKE, WithCtrl(KeyEvent.VK_SPACE), new PlaceSnakeListener(gameCreator, mapEditorFrame));
    }
}
