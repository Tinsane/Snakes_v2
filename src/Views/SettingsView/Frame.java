package Views.SettingsView;

import Views.MainMenuView.MainMenuRestorer;
import Views.Utils.TextButton;

import javax.swing.*;
import java.awt.*;

/*
Settings:
    1) Choose game style
    2) Choose game speed.
    3) Volume (in future)
*/

/**
 * Created by Владимир on 25.10.2016.
 */
public class Frame extends JFrame
{
    public Frame(Views.MainMenuView.Frame mainMenuFrame)
    {
        super();
        addWindowListener(new MainMenuRestorer(this, mainMenuFrame));
        setBackground(Color.GREEN);
        Container pane = getContentPane();
        pane.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        TextButton applyButton = new TextButton("Apply", new Font("Tahoma", Font.BOLD, 40),
                Color.YELLOW, new Color(255, 215, 0));
        constraints.gridx = 0;
        constraints.gridy = 0;
        pane.add(applyButton, constraints);

        TextButton cancelButton = new TextButton("Cancel", new Font("Tahoma", Font.BOLD, 40),
                Color.YELLOW, new Color(255, 215, 0));
        cancelButton.addActionListener(e -> {
            dispose();
            mainMenuFrame.setVisible(true);
        });
        constraints.gridx = 1;
        pane.add(cancelButton, constraints);
        pack();

/*
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        TextButton applyButton = new TextButton("Apply", new Font("Tahoma", Font.BOLD, 40),
                Color.YELLOW, new Color(255, 215, 0));
        applyButton.addActionListener(e -> {});
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.weighty = 1.0;
        //constraints.anchor = GridBagConstraints.PAGE_END;
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(applyButton, constraints);

        TextButton cancelButton = new TextButton("Cancel", new Font("Tahoma", Font.BOLD, 40),
                Color.YELLOW, new Color(255, 215, 0));
        cancelButton.addActionListener(e -> {
            dispose();
            mainMenuFrame.setVisible(true);
        });
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.weighty = 1.0;
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(cancelButton, constraints);
*/

        setVisible(true);
    }
}
