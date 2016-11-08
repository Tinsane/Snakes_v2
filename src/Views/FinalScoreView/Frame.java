package Views.FinalScoreView;

import Views.Styles.MenuStyle;
import Views.Utils.ButtonUtils.CloseFrameButton;
import Views.Utils.ButtonUtils.MenuButton;
import Views.Utils.PanelUtils.MenuPanel;
import Views.Utils.ParentFrameRestorer;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Владимир on 24.10.2016.
 */
public class Frame extends JFrame
{
    public Frame(Views.MainMenuView.Frame mainMenuFrame, int finalScore)
    {
        super();
        addWindowListener(new ParentFrameRestorer(this, mainMenuFrame));

        JLabel scoreLabel = new JLabel("Final Score: " + finalScore, SwingConstants.CENTER);
        scoreLabel.setForeground(MenuStyle.TEXT_COLOR);
        scoreLabel.setFont(MenuStyle.getFont(40));

        MenuButton mainMenuButton = new CloseFrameButton("Main Menu", 40, this);

        add(new MenuPanel(new Component[] {scoreLabel, mainMenuButton}));

        setSize(300, 400);
        setVisible(true);
    }
}
