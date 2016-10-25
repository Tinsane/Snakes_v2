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
    private final Color focusedColor;
    private final Color nonFocusedColor;
    public TextButton(String text, Font font)
    {
        this(text, font, Color.black, Color.gray);
    }
    public TextButton(String text, Font font, Color nonFocusedColor, Color focusedColor)
    {
        super();
        this.text = text;
        this.font = font;
        this.setBorder(null);
        this.setContentAreaFilled(false);
        this.focusedColor = focusedColor;
        this.nonFocusedColor = nonFocusedColor;
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
        if (hasFocus())
            g2d.setColor(focusedColor);
        else
            g2d.setColor(nonFocusedColor);
        g2d.drawString(text, (getVisibleRect().width - metrics.stringWidth(text)) / 2,
                (getVisibleRect().height - metrics.getHeight()) / 2 + metrics.getAscent());
    }
}
