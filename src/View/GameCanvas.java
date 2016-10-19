package View;

import Core.Game.Game;
import View.Styles.GameDrawer;
import View.Styles.GameStyle;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Владимир on 11.10.2016.
 */
public class GameCanvas extends JPanel
{
    Game game;
    GameStyle style;
    private double goneTurnPart;

    GameCanvas(Game game, GameStyle style, boolean doubleBuffered)
    {
        super(doubleBuffered);
        this.game = game;
        this.style = style;
    }

    public void repaint(double goneTurnPart)
    {
        this.goneTurnPart = goneTurnPart;
        repaint();
    }

    @Override
    public void paint(Graphics g)
    {
        GameDrawer drawer = style.CreateDrawer((Graphics2D) g, game, goneTurnPart);
        drawer.draw();
    }
}
