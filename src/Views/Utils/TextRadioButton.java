package Views.Utils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Владимир on 20.10.2016.
 */
public class TextRadioButton extends JToggleButton
{
    private final String text;
    private final Font font;
    private final Color selectedColor;
    private final Color regularColor;
    public TextRadioButton(String text, Font font)
    {
        this(text, font, Color.black, Color.gray);
    }
    public TextRadioButton(String text, Font font, Color regularColor, Color selectedColor)
    {
        super();
        this.text = text;
        this.font = font;
        this.setBorder(null);
        this.setContentAreaFilled(false);
        this.selectedColor = selectedColor;
        this.regularColor = regularColor;
    }
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        FontMetrics metrics = g2d.getFontMetrics(font);
        g2d.setFont(font);
        if (isSelected())
            g2d.setColor(selectedColor);
        else
            g2d.setColor(regularColor);
        g2d.drawString(text, (getVisibleRect().width - metrics.stringWidth(text)) / 2,
                (getVisibleRect().height - metrics.getHeight()) / 2 + metrics.getAscent());
    }
}
