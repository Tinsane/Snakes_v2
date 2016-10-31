package Views.FinalScoreView;

import Views.Styles.MenuStyle;
import Views.Utils.MenuButton;
import Views.Utils.ParentFrameRestorer;
import Views.Utils.TextButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

/**
 * Created by Владимир on 24.10.2016.
 */
public class Frame extends JFrame
{
    public Frame(Views.MainMenuView.Frame mainMenuFrame, int finalScore)
    {
        super();
        addWindowListener(new ParentFrameRestorer(this, mainMenuFrame));
        //TODO: code review by Van
        //TODO: add(new FinalScorePanel(menuStyle));
        JPanel panel = new JPanel();
        panel.setBackground(MenuStyle.backgroundColor);
        add(panel);
        panel.setLayout(new GridLayout(2, 1));

        JLabel scoreLabel = new JLabel("Final Score: " + finalScore, SwingConstants.CENTER);
        // TODO: code review by Van
        scoreLabel.setForeground(Color.YELLOW); // TODO: to menuStyle
        scoreLabel.setFont(MenuStyle.getFont(40)); // TODO: to menuStyle
        panel.add(scoreLabel);

        MenuButton mainMenuButton = new MenuButton("Main Menu", 40);
        mainMenuButton.addActionListener(e -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));
        panel.add(mainMenuButton);

        setSize(300, 400);
        setVisible(true);
    }
}
