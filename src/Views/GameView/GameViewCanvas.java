package Views.GameView;

import Core.Game.GameAlike;
import Views.Styles.Drawer;
import Views.Styles.GameStyle;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Владимир on 11.10.2016.
 */
public class GameViewCanvas extends JPanel
{
    private final GameAlike game;
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
        Drawer drawer = style.CreateDrawer((Graphics2D) g, game, goneTurnPart);
        drawer.draw();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(new Font("Tahoma", Font.PLAIN, 30));
        g2d.drawString("Score: " + game.getSnake().getLength(), 0, 30);
    }
}
