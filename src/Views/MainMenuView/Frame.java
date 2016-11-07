package Views.MainMenuView;

import Core.Game.GameCreator;
import Core.MapObjects.StaticMapObjects.Wall;
import Views.GameView.Settings;
import Views.RecordsView.RecordsFrame;
import Views.Styles.Default.DefaultStyle;
import Views.Utils.TextButton;

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

        JPanel panel = new JPanel();
        add(panel);
        panel.setBackground(Color.GREEN);
        panel.setLayout(new GridLayout(5, 1));
        Font buttonFont = new Font("Tahoma", Font.BOLD, 45);

        TextButton startGameButton = new TextButton("Start Game", buttonFont, Color.YELLOW, new Color(255, 215, 0));
        startGameButton.addActionListener(e -> {
            setVisible(false);
            GameCreator creator = new GameCreator();
            creator.setMapSize(10, 10);
            creator.placeWall(5, 5);
            creator.placeMapObjectsInLineX(4, 1, 7, new Wall());
            creator.placeSnake(0, 0, 1);
            Views.GameView.Frame gameFrame =
                    new Views.GameView.Frame(this, creator.createGame(), settings);
            gameFrame.start();
        });

        TextButton mapEditorButton = new TextButton("Map Editor", buttonFont, Color.YELLOW, new Color(255, 215, 0));
        mapEditorButton.addActionListener(e -> {
            setVisible(false);
            try
            {
                new Views.MapEditorView.Frame(this);
            }
            catch (IOException exception)
            {
                JOptionPane.showMessageDialog(this, "Error: can't load something.",
                        "Snake feels herself sick", JOptionPane.ERROR_MESSAGE);
                setVisible(true);
            }
        });

        TextButton scoreboardButton = new TextButton("Scoreboard", buttonFont, Color.YELLOW, new Color(255, 215, 0));
        scoreboardButton.addActionListener(e ->
        {
            setVisible(false);
            new RecordsFrame(this);
        });
        TextButton settingsButton = new TextButton("Settings", buttonFont, Color.YELLOW, new Color(255, 215, 0));
        settingsButton.addActionListener(e -> {
            setVisible(false);
            new Views.SettingsView.Frame(this, settings);
        });

        TextButton exitButton = new TextButton("Exit", buttonFont, Color.YELLOW, new Color(255, 215, 0));
        exitButton.addActionListener(e -> {setVisible(false); dispose();});

        panel.add(startGameButton);
        panel.add(mapEditorButton);
        panel.add(scoreboardButton);
        panel.add(settingsButton);
        panel.add(exitButton);
        setVisible(true);
    }

    private BufferedImage loadImage(String filename) throws IOException
    {
        return ImageIO.read(getClass().getResource(filename));
    }
}
