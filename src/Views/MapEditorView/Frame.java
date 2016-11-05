package Views.MapEditorView;

import Controllers.MapEditorController;
import Core.Game.GameCreatorWrapper;
import Views.MainMenuView.MainMenuRestorer;
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
    private MapEditorStyle style;

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
        objectPanel = new ObjectPanel(style, gameCreator, true);
        this.style = style;

        addKeyListener(new MapEditorController(gameCreator, this));

        setVisible(true);
        setResizable(false);

        setLayout(new BorderLayout());
        add(objectPanel, BorderLayout.PAGE_START);
        add(canvas, BorderLayout.CENTER);

        update();
    }

    public void update()
    {
        setSize(placedVertical(canvas.getPreferredSize(), objectPanel.getPreferredSize()));
//        setSize(gameCreator.getWidth() * style.getTileSize(),
//                gameCreator.getHeight() * style.getTileSize() + style.getMapObjectWrapper().getHeight());
        objectPanel.repaint();
        canvas.repaint();
    }

    private Dimension placedVertical(Dimension... dimensions)
    {
        return Arrays
                .stream(dimensions)
                .reduce((x, y) -> new Dimension((int)Math.max(x.getWidth(), y.getWidth()),
                                                (int)(x.getHeight() + y.getHeight())))
                .get();
    }
}
