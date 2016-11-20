package Views.GameView;

import Core.Game.Game;
import Views.Styles.GameStyle;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Владимир on 11.10.2016.
 */
public class GameCanvas extends JPanel
{
    protected final Game game;
    private GameStyle style;
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
        Graphics2D g2d = (Graphics2D) g;
        style.CreateDrawer(g2d, game, goneTurnPart).draw();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setFont(new Font("Tahoma", Font.PLAIN, 30));
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(
                game.getWidth() * style.getTileSize(),
                game.getHeight() * style.getTileSize());
    }
}
