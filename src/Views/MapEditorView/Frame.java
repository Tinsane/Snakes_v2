package Views.MapEditorView;

import Controllers.MapEditorController;
import Core.Game.GameCreatorWrapper;
import Views.Utils.ParentFrameRestorer;
import Views.Styles.Default.DefaultStyle;
import Views.Styles.GameStyle;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by Владимир on 20.10.2016.
 */
public class Frame extends JFrame
{
    private GameCreatorWrapper gameCreator;
    private MapEditorCanvas canvas;

    public Frame(GameStyle style)
    {
        super();
        setTitle("SnakeMapEditor");

        gameCreator = new GameCreatorWrapper(5, 5);
        try
        {
            canvas = new MapEditorCanvas(gameCreator, style, true);
        }
        catch (IOException exception)
        {
            JOptionPane.showMessageDialog(this, "Error: Can not load textures.",
                    "Snake feels herself sick", JOptionPane.ERROR_MESSAGE);
        }
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
