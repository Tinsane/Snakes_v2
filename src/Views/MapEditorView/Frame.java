package Views.MapEditorView;

import Controllers.MapEditorController;
import Core.Game.GameCreator;
import Views.MainMenuView.MainMenuRestorer;
import Views.Styles.Default.DefaultStyle;
import Views.Styles.GameStyle;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by Владимир on 20.10.2016.
 */
public class Frame extends JFrame
{
    GameStyle style;
    GameCreator gameCreator;
    public Frame(Views.MainMenuView.Frame mainMenuFrame) throws IOException
    {
        this(mainMenuFrame, new DefaultStyle());
    }
    Frame(Views.MainMenuView.Frame mainMenuFrame, GameStyle style)
    {
        super();
        setTitle("SnakeMapEditor");
        addWindowListener(new MainMenuRestorer(this, mainMenuFrame));

        this.style = style;
        gameCreator = new GameCreator();

        addKeyListener(new MapEditorController(gameCreator));

        setVisible(true);
    }
}
