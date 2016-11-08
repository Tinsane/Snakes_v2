package Views.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Владимир on 20.10.2016.
 */
public class TextButton extends JButton
{
    private final String text;
    private final Font font;
    private final Color pointedColor;
    private final Color regularColor;
    private boolean isPointed;
    public TextButton(String text, Font font, Color regularColor, Color pointedColor)
    {
        super();
        this.text = text;
        this.font = font;
        this.setBorder(null);
        this.setContentAreaFilled(false);
        this.pointedColor = pointedColor;
        this.regularColor = regularColor;
        isPointed = false;
        addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
            }

            @Override
            public void mousePressed(MouseEvent e)
            {
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
            }

            @Override
            public void mouseEntered(MouseEvent e)
            {
                isPointed = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                isPointed = false;
                repaint();
            }
        });
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        FontMetrics metrics = g2d.getFontMetrics(font);
        g2d.setFont(font);
        if (isPointed)
            g2d.setColor(pointedColor);
        else
            g2d.setColor(regularColor);
        g2d.drawString(text, (getVisibleRect().width - metrics.stringWidth(text)) / 2,
                (getVisibleRect().height - metrics.getHeight()) / 2 + metrics.getAscent());
    }
}
