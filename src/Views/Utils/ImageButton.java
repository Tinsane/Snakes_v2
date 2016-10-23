package Views.Utils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Владимир on 20.10.2016.
 */
public class ImageButton extends JButton
{
    private final Image image;
    public ImageButton(Image image)
    {
        super();
        this.setBorder(null);
        this.setContentAreaFilled(false);
        this.image = image;
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
}
