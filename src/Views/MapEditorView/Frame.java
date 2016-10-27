package Views.MapEditorView;

import Controllers.MapEditorController;
import Core.Game.GameCreatorWrapper;
import Views.MainMenuView.MainMenuRestorer;
import Views.Styles.Default.DefaultStyle;
import Views.Styles.Default.MapEditorDefaultStyle;
import Views.Styles.GameStyle;

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

    public Frame(Views.MainMenuView.Frame mainMenuFrame) throws IOException
    {
        this(mainMenuFrame, new DefaultStyle());
    }

    public Frame(Views.MainMenuView.Frame mainMenuFrame, GameStyle style) throws IOException
    {
        super();
        setTitle("SnakeMapEditor");
        addWindowListener(new MainMenuRestorer(this, mainMenuFrame));

        gameCreator = new GameCreatorWrapper(5, 5);
        canvas = new MapEditorCanvas(gameCreator, new MapEditorDefaultStyle(), true);

        setSize((gameCreator.getWidth() + 1) * style.getTileSize(),
                (gameCreator.getHeight() + 1) * style.getTileSize());

        addKeyListener(new MapEditorController(gameCreator, this));

        setVisible(true);
        add(canvas);
        update();
    }

    public void update()
    {
        canvas.repaint();
    }
}
