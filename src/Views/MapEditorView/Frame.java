package Views.MapEditorView;

import Controllers.MapEditorControllers.MapEditorController;
import Core.Game.GameCreatorWrapper;
import Views.Styles.GameStyle;
import Views.Utils.ParentFrameRestorer;
import Views.Styles.Default.MapEditorDefaultStyle;
import Views.Styles.MapEditorStyle;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Владимир on 20.10.2016.
 */
public class Frame extends JFrame
{
    private GameCreatorWrapper gameCreator;
    private MapEditorCanvas canvas;
    private ObjectPanel objectPanel;

    public Frame() throws IOException
    {
        this(new MapEditorDefaultStyle());
    }

    public Frame(MapEditorStyle style) throws IOException
    {
        super();
        setTitle("SnakeMapEditor");
        //addWindowListener(new ParentFrameRestorer(this, mainMenuFrame));

        gameCreator = new GameCreatorWrapper(5, 5);
        canvas = new MapEditorCanvas(gameCreator, new MapEditorDefaultStyle(), true);
        objectPanel = new ObjectPanel(style, gameCreator, true);

        addKeyListener(new MapEditorController(gameCreator, this));

        setVisible(true);
        setResizable(false);

        setJMenuBar(new MapEditorMenuBar(gameCreator, this));

        setLayout(new BorderLayout());
        add(objectPanel, BorderLayout.PAGE_START);
        add(canvas, BorderLayout.CENTER);

        update();
    }

    public void update()
    {
        setSize(placedVertically(
                canvas.getPreferredSize(),
                objectPanel.getPreferredSize(),
                getMinimumSize())); // minimum size is sum size of menu bar and up border of window
        objectPanel.update();
        canvas.repaint();
    }

    private Dimension placedVertically(Dimension... dimensions)
    {
        return Arrays
                .stream(dimensions)
                .reduce((x, y) -> new Dimension((int)Math.max(x.getWidth(), y.getWidth()),
                                                (int)(x.getHeight() + y.getHeight())))
                .get();
    }
}
