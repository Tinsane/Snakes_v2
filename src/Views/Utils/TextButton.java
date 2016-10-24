package Views.Utils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Владимир on 20.10.2016.
 */
public class TextButton extends JButton
{
    private final String text;
    private final Font font;
    public TextButton(String text, Font font)
    {
        super();
        this.text = text;
        this.font = font;
        this.setBorder(null);
        this.setContentAreaFilled(false);
        setBorder(null);
        setContentAreaFilled(false);
        setFocusPainted(false);
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        FontMetrics metrics = g2d.getFontMetrics(font);
        g2d.setFont(font);
        if (isFocusOwner())
            g2d.setColor(Color.YELLOW);
        else
            g2d.setColor(Color.GREEN);
        g2d.drawString(text, (getVisibleRect().width - metrics.stringWidth(text)) / 2,
                (getVisibleRect().height - metrics.getHeight()) / 2 + metrics.getAscent());
    }
}
