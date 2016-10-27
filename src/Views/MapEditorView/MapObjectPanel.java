package Views.MapEditorView;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by ISmir on 27.10.2016.
 */
public class MapObjectPanel extends JPanel
{
    BufferedImage objectImage;

    public MapObjectPanel(BufferedImage objectImage)
    {
        this.objectImage = objectImage;
        setSize(objectImage.getWidth(), objectImage.getHeight());
    }

    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(objectImage, 0, 0, null);
    }
}
