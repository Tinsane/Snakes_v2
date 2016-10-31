package Views.MapEditorView;

import Controllers.MapEditorController;
import Core.Game.GameCreatorWrapper;
import Views.MainMenuView.MainMenuRestorer;
import Views.Styles.Default.MapEditorDefaultStyle;
import Views.Styles.MapEditorStyle;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Владимир on 20.10.2016.
 */
public class Frame extends JFrame
{
    private GameCreatorWrapper gameCreator;
    private MapEditorCanvas canvas;
    private ObjectPanel objectPanel;

    public Frame(Views.MainMenuView.Frame mainMenuFrame) throws IOException
    {
        this(mainMenuFrame, new MapEditorDefaultStyle());
    }

    public Frame(Views.MainMenuView.Frame mainMenuFrame, MapEditorStyle style) throws IOException
    {
        super();
        setTitle("SnakeMapEditor");
        addWindowListener(new MainMenuRestorer(this, mainMenuFrame));

        gameCreator = new GameCreatorWrapper(5, 5);
        canvas = new MapEditorCanvas(gameCreator, new MapEditorDefaultStyle(), true);
        objectPanel = new ObjectPanel(style, true);
        objectPanel.setMaximumSize(new Dimension(100, 20));

        setSize((gameCreator.getWidth() + 1) * style.getTileSize(),
                (gameCreator.getHeight() + 1) * style.getTileSize());

        addKeyListener(new MapEditorController(gameCreator, this));

//        setVisible(true);
//        SpringLayout layout = new SpringLayout();
//        SpringLayout.Constraints constraints = new SpringLayout.Constraints();
//
//        setLayout(new SpringLayout());
//        add(objectPanel, SpringLayout.NORTH);
//        add(canvas, SpringLayout.VERTICAL_CENTER);
        setLayout(new GridLayout(2, 1));
        add(objectPanel);
        add(canvas);
        update();
    }

    public void update()
    {
        objectPanel.repaint();
        canvas.repaint();
    }
}
