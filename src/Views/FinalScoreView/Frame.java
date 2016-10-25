package Views.FinalScoreView;

import Views.MainMenuView.MainMenuRestorer;
import Views.Utils.TextButton;

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
        addWindowListener(new MainMenuRestorer(this, mainMenuFrame));
        JPanel panel = new JPanel();
        panel.setBackground(Color.GREEN);
        add(panel);
        panel.setLayout(new GridLayout(2, 1));

        JLabel scoreLabel = new JLabel("Final Score: " + finalScore, SwingConstants.CENTER);
        scoreLabel.setForeground(Color.YELLOW);
        scoreLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
        panel.add(scoreLabel);

        TextButton mainMenuButton = new TextButton("Main Menu", new Font("Tahoma", Font.BOLD, 40),
                Color.YELLOW, new Color(255, 215, 0));
        mainMenuButton.addActionListener(e -> {
            setVisible(false);
            dispose();
            mainMenuFrame.setVisible(true);
        });
        panel.add(mainMenuButton);

        setSize(300, 400);
        setResizable(false);
        setVisible(true);
    }
}
