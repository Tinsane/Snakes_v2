package Views.FinalScoreView;

import Views.Utils.ButtonUtils.CloseFrameButton;
import Views.Utils.ButtonUtils.MenuButton;
import Views.Utils.PanelUtils.MenuPanel;
import Views.Utils.ParentFrameRestorer;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Владимир on 24.10.2016.
 */
public abstract class Frame extends JFrame
{
    public Frame(Views.MainMenuView.Frame mainMenuFrame, Runnable restart, Component scoreComponent)
    {
        super();
        addWindowListener(new ParentFrameRestorer(this, mainMenuFrame));

        MenuButton restartButton = new MenuButton("Restart", 40);
        restartButton.addActionListener(e ->
        {
            setVisible(false);
            dispose();
            restart.run();
        });

        MenuButton mainMenuButton = new CloseFrameButton("Main Menu", 40, this);

        add(new MenuPanel(new Component[] {scoreComponent, restartButton, mainMenuButton}));

        setSize(600, 400);
        setVisible(true);
    }
}
