package Views.MainMenuView;

import Views.GameView.Settings;
import Views.MainMenuView.Buttons.*;
import Views.Styles.Default.DefaultStyle;
import Views.Styles.MenuStyle;
import Views.Utils.MenuButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Владимир on 20.10.2016.
 */
public class Frame extends JFrame
{
    private final int buttonFontSize = 45;
    public Settings settings;
    public Frame() throws IOException
    {
        super();
        settings = new Settings(new DefaultStyle());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("SnakeMainMenu");
        setIconImage(loadImage("snakeIcon.png"));

        setSize(400, 400);
        setResizable(false);

        MenuButton buttons[] = new MenuButton[] {
                new StartGameButton("Start Game", buttonFontSize, this),
                new MapEditorButton("Map Editor", buttonFontSize, this),
                new ScoreboardButton("Scoreboard", buttonFontSize, this),
                new SettingsButton("Settings", buttonFontSize, this),
                new ExitButton("Exit", buttonFontSize, this)};

        //TODO: code review by Van
        //TODO: add(new MainMenuPanel(buttons, menuStyle));
        JPanel panel = new JPanel();
        add(panel);
        panel.setBackground(MenuStyle.backgroundColor);
        panel.setLayout(new GridLayout(buttons.length, 1));
        for(MenuButton button : buttons)
            panel.add(button);
        setVisible(true);
    }

    private BufferedImage loadImage(String filename) throws IOException
    {
        return ImageIO.read(getClass().getResource(filename));
    }
}
