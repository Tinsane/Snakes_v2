package Views.MapEditorView;

import Controllers.MapEditorController;
import Core.Game.GameCreator;
import Views.Styles.Default.DefaultStyle;
import Views.Styles.GameStyle;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * Created by Владимир on 20.10.2016.
 */
public class Frame extends JFrame
{
    private final Views.MainMenuView.Frame mainMenuFrame;
    GameStyle style;
    GameCreator gameCreator;
    public Frame(Views.MainMenuView.Frame mainMenuFrame) throws IOException
    {
        this(mainMenuFrame, new DefaultStyle());
    }
    Frame(Views.MainMenuView.Frame mainMenuFrame, GameStyle style)
    {
        super();
        this.mainMenuFrame = mainMenuFrame;
        setTitle("SnakeMapEditor");
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                super.windowClosing(e);
                dispose();
                mainMenuFrame.setVisible(true);
            }
        });

        this.style = style;
        gameCreator = new GameCreator();

        addKeyListener(new MapEditorController(gameCreator));

        setVisible(true);
    }
}
