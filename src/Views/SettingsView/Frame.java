package Views.SettingsView;

import Views.GameView.Settings;
import Views.SettingsView.Widgets.ApplyButton;
import Views.Styles.MenuStyle;
import Views.Utils.ButtonUtils.CloseFrameButton;
import Views.Utils.ButtonUtils.MenuButton;
import Views.Utils.ButtonUtils.TextRadioButton;

import javax.swing.*;
import java.awt.*;

/*
Settings:
    1) Choose game singlePlayerStyle
    2) Choose game speed
    3) Volume (in future)
*/

/**
 * Created by Владимир on 25.10.2016.
 */
public class Frame extends JFrame
{
    public Settings settings;
    public Frame(Views.MainMenuView.Frame mainMenuFrame)
    {
        super();
        settings = mainMenuFrame.settings;
        Container pane = getContentPane();
        pane.setBackground(MenuStyle.BACKGROUND_COLOR);
        pane.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        Box speedBox = new Box(BoxLayout.X_AXIS);
        JLabel speedLabel = new JLabel("Speed:", SwingConstants.LEFT);
        speedLabel.setForeground(MenuStyle.TEXT_COLOR);
        speedLabel.setFont(MenuStyle.getFont(40));
        speedBox.add(speedLabel);
        ButtonGroup speedGroup = new ButtonGroup();
        TextRadioButton button1x = new TextRadioButton("1x", MenuStyle.getFont(40),
                MenuStyle.TEXT_COLOR, MenuStyle.PRESSED_BUTTON_COLOR);
        speedGroup.add(button1x);
        speedBox.add(button1x);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        pane.add(speedBox, constraints);


        MenuButton applyButton = new ApplyButton(40, this, mainMenuFrame);
        constraints.gridwidth = 1;
        constraints.weighty = 0.3;
        constraints.weightx = 0.5;
        constraints.gridx = 0;
        constraints.gridy = 1;
        pane.add(applyButton, constraints);

        MenuButton cancelButton = new CloseFrameButton("Cancel", 40, this);
        constraints.gridx = 1;
        constraints.gridy = 1;
        pane.add(cancelButton, constraints);
        setSize(400, 400);

        setVisible(true);
    }
}
