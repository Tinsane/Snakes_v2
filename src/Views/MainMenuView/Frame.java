package Views.MainMenuView;

import Views.GameView.Settings;
import Views.MainMenuView.Buttons.*;
import Views.Styles.Default.DefaultStyle;
import Views.Utils.MenuButton;
import Views.Utils.MenuPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
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

        MenuButton buttons[] = {
                new StartGameButton(buttonFontSize, this),
                new MapEditorButton(buttonFontSize, this),
                new ScoreboardButton(buttonFontSize, this),
                new SettingsButton(buttonFontSize, this),
                new ExitButton(buttonFontSize, this)};

        add(new MenuPanel(buttons));
        setVisible(true);
    }

    private BufferedImage loadImage(String filename) throws IOException
    {
        return ImageIO.read(getClass().getResource(filename));
    }
}
