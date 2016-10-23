package Views.MainMenuView;

import Core.Game.GameCreator;
import Core.MapObjects.StaticMapObjects.Wall;
import Views.GameView.Settings;
import Views.Styles.Default.DefaultStyle;
import Views.Utils.ImageButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Владимир on 20.10.2016.
 */
public class Frame extends JFrame
{
    public Frame() throws IOException
    {
        super();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("SnakeMainMenu");
        setIconImage(loadImage("snakeIcon.png"));

        setSize(400, 400);
        setResizable(false);

        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(new GridLayout(4, 1));

        JButton startGameButton = new JButton();
        startGameButton.setFont(new Font("Tahoma", Font.BOLD, 45));
        startGameButton.setForeground(Color.green);
        startGameButton.setBorder(null);
        startGameButton.setContentAreaFilled(false);
        startGameButton.setFocusPainted(false);
        startGameButton.setText("Start Game");
        startGameButton.addActionListener(e -> {
            setVisible(false);
            dispose();
            try
            {
                GameCreator creator = new GameCreator();
                creator.setMapSize(10, 10);
                creator.placeWall(5, 5);
                creator.placeMapObjectsInLineX(4, 1, 7, new Wall());
                Views.GameView.Frame gameFrame =
                        new Views.GameView.Frame(this, creator.createGame(0, 0, 1), new Settings(new DefaultStyle()));
                gameFrame.start();
            } catch (IOException exception)
            {
                // TODO: something
            }
        });

        JButton mapEditorButton = new JButton();
        mapEditorButton.setFont(new Font("Tahoma", Font.BOLD, 45));
        mapEditorButton.setForeground(Color.green);
        mapEditorButton.setBorder(null);
        mapEditorButton.setContentAreaFilled(false);
        mapEditorButton.setFocusPainted(false);
        mapEditorButton.setText("Map Editor");
        mapEditorButton.addActionListener(e -> {
            setVisible(false);
            dispose();
            try
            {
                new Views.MapEditorView.Frame(this);
            }
            catch (IOException exception)
            {
                // TODO: something
            }
        });

        JButton settingsButton = new JButton();
        settingsButton.setFont(new Font("Tahoma", Font.BOLD, 45));
        settingsButton.setForeground(Color.green);
        settingsButton.setBorder(null);
        settingsButton.setContentAreaFilled(false);
        settingsButton.setFocusPainted(false);
        settingsButton.setText("Settings");

        JButton exitButton = new JButton();
        exitButton.setFont(new Font("Tahoma", Font.BOLD, 45));
        exitButton.setForeground(Color.green);
        exitButton.setBorder(null);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);
        exitButton.setText("Exit");
        exitButton.addActionListener(e -> {setVisible(false); dispose();});

        panel.add(startGameButton);
        panel.add(mapEditorButton);
        panel.add(settingsButton);
        panel.add(exitButton);
        setVisible(true);
    }

    private BufferedImage loadImage(String filename) throws IOException
    {
        return ImageIO.read(getClass().getResource(filename));
    }
}
