package Views.FinalScoreView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Владимир on 24.10.2016.
 */
public class Canvas extends JPanel
{
    private final int score;
    Canvas(int score)
    {
        this.score = score;
    }
    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GREEN);
        g2d.fill(getVisibleRect());
        g2d.setColor(Color.YELLOW);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("Tahoma", Font.PLAIN, 40);
        FontMetrics metrics = g2d.getFontMetrics(font);
        g2d.setFont(font);
        String text = "Score: " + score;
        g2d.drawString(text, (getVisibleRect().width - metrics.stringWidth(text)) / 2,
                (getVisibleRect().height - metrics.getHeight()) / 2 + metrics.getAscent());
    }
}
