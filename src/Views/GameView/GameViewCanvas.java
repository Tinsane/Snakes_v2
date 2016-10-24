package Views.GameView;

import Core.Game.Game;
import Core.Game.GameAlike;
import Core.MapObjects.MapObjectVisitor;
import Views.Styles.GameStyle;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Владимир on 11.10.2016.
 */
public class GameViewCanvas extends JPanel
{
    private GameAlike game;
    private GameStyle style;
    private double goneTurnPart;

    GameViewCanvas(GameAlike game, GameStyle style, boolean doubleBuffered)
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
        MapObjectVisitor drawer = style.CreateDrawer((Graphics2D) g, game, goneTurnPart);
        drawer.visitAll();
    }
}
