package Views.MainMenuView;

import Core.Game.GameCreator;
import Core.MapObjects.StaticMapObjects.Wall;
import Views.GameView.Settings;
import Views.Styles.Default.DefaultStyle;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Владимир on 20.10.2016.
 */
public class Frame extends JFrame
{
    public Frame()
    {
        super();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("SnakeMainMenu");

        setSize(200, 200);

        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(new GridLayout(4, 1));

        JButton startGameButton = new JButton();
        startGameButton.setBorder(null);
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
        mapEditorButton.setBorder(null);
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
        settingsButton.setBorder(null);
        settingsButton.setText("Settings");

        JButton exitButton = new JButton();
        exitButton.setBorder(null);
        exitButton.setText("Exit");
        exitButton.addActionListener(e -> {setVisible(false); dispose();});

        panel.add(startGameButton);
        panel.add(mapEditorButton);
        panel.add(settingsButton);
        panel.add(exitButton);
        setVisible(true);
    }
}
