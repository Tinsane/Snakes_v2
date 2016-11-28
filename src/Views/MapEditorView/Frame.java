package Views.MapEditorView;

import Controllers.MapEditorControllers.MapEditorController;
import Core.Game.GameCreators.GameCreatorWrapper;
import Views.GameView.Settings;
import Views.Styles.Default.MapEditorDefaultStyle;
import Views.Styles.MapEditorStyle;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static Views.Utils.DimensionUtils.placedVertically;

/**
 * Created by Владимир on 20.10.2016.
 */
public class Frame extends JFrame
{
    private GameCreatorWrapper gameCreator;
    private MapEditorCanvas canvas;
    private ObjectPanel objectPanel;

    public Frame(Settings settings) throws IOException
    {
        this(new MapEditorDefaultStyle(), settings);
    }

    public Frame(MapEditorStyle style, Settings settings) throws IOException
    {
        super();
        setTitle("SnakeMapEditor");
        //addWindowListener(new ParentFrameRestorer(this, mainMenuFrame));

        gameCreator = new GameCreatorWrapper(5, 5, settings.gameUpdatingSystem);
        canvas = new MapEditorCanvas(gameCreator, style, true);
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
}
