package Views.GameView;

import Core.Game.Game;
import Core.GameObjects.Snake;
import Views.Styles.GameStyle;

import java.awt.*;

/**
 * Created by Владимир on 20.11.2016.
 */
public class SinglePlayerGameCanvas extends GameCanvas
{
    SinglePlayerGameCanvas(Game game, GameStyle style, boolean doubleBuffered)
    {
        super(game, style, doubleBuffered);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawString("Score: " + Snake.getSnake(game).getLength(), 0, 30);
    }
}
