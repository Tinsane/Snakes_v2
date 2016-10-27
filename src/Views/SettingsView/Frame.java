package Views.SettingsView;

import Views.GameView.Settings;
import Views.MainMenuView.MainMenuRestorer;
import Views.Utils.TextButton;
import Views.Utils.TextRadioButton;

import javax.swing.*;
import java.awt.*;

/*
Settings:
    1) Choose game style
    2) Choose game speed
    3) Volume (in future)
*/

/**
 * Created by Владимир on 25.10.2016.
 */
public class Frame extends JFrame
{
    private Settings settings;
    public Frame(Views.MainMenuView.Frame mainMenuFrame, Settings settings)
    {
        super();
        this.settings = settings;
        addWindowListener(new MainMenuRestorer(this, mainMenuFrame));
        Container pane = getContentPane();
        pane.setBackground(Color.GREEN);
        pane.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        Box speedBox = new Box(BoxLayout.X_AXIS);
        JLabel speedLabel = new JLabel("Speed:", SwingConstants.LEFT);
        speedLabel.setForeground(Color.YELLOW);
        speedLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
        speedBox.add(speedLabel);
        ButtonGroup speedGroup = new ButtonGroup();
        TextRadioButton button1x = new TextRadioButton("1x", new Font("Tahoma", Font.BOLD, 40),
                Color.YELLOW, new Color(255, 215, 0));
        speedGroup.add(button1x);
        speedBox.add(button1x);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        pane.add(speedBox, constraints);

        TextButton applyButton = new TextButton("Apply", new Font("Tahoma", Font.BOLD, 40),
                Color.YELLOW, new Color(255, 215, 0));
        applyButton.addActionListener(e -> {
            mainMenuFrame.settings = this.settings;
            dispose();
            mainMenuFrame.setVisible(true);
        });
        constraints.gridwidth = 1;
        constraints.weighty = 0.3;
        constraints.weightx = 0.5;
        constraints.gridx = 0;
        constraints.gridy = 1;
        pane.add(applyButton, constraints);

        TextButton cancelButton = new TextButton("Cancel", new Font("Tahoma", Font.BOLD, 40),
                Color.YELLOW, new Color(255, 215, 0));
        cancelButton.addActionListener(e -> {
            dispose();
            mainMenuFrame.setVisible(true);
        });
        constraints.gridx = 1;
        constraints.gridy = 1;
        pane.add(cancelButton, constraints);
        setSize(400, 400);

        setVisible(true);
    }
}
