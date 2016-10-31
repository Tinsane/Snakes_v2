package Views.SettingsView;

import Views.GameView.Settings;
import Views.Styles.MenuStyle;
import Views.Utils.MenuButton;
import Views.Utils.TextRadioButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

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
    public Frame(Views.MainMenuView.Frame mainMenuFrame)
    {
        //TODO: code review by Van
        //TODO: Слишком много кода - декомпозируй.
        super();
        settings = mainMenuFrame.settings;
        Container pane = getContentPane();
        pane.setBackground(MenuStyle.backgroundColor);
        pane.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        Box speedBox = new Box(BoxLayout.X_AXIS);
        JLabel speedLabel = new JLabel("Speed:", SwingConstants.LEFT);
        speedLabel.setForeground(MenuStyle.textColor);
        speedLabel.setFont(MenuStyle.getFont(40));
        speedBox.add(speedLabel);
        ButtonGroup speedGroup = new ButtonGroup();
        TextRadioButton button1x = new TextRadioButton("1x", MenuStyle.getFont(40),
                MenuStyle.textColor, MenuStyle.pressedButtonColor);
        speedGroup.add(button1x);
        speedBox.add(button1x);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        pane.add(speedBox, constraints);

        MenuButton applyButton = new MenuButton("Apply", 40);
        applyButton.addActionListener(e -> {
            mainMenuFrame.settings = this.settings;
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        });
        constraints.gridwidth = 1;
        constraints.weighty = 0.3;
        constraints.weightx = 0.5;
        constraints.gridx = 0;
        constraints.gridy = 1;
        pane.add(applyButton, constraints);

        MenuButton cancelButton = new MenuButton("Cancel", 40);
        cancelButton.addActionListener(e -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));
        constraints.gridx = 1;
        constraints.gridy = 1;
        pane.add(cancelButton, constraints);
        setSize(400, 400);

        setVisible(true);
    }
}
