package Views.MapEditorView;

import Core.MapObjects.MapObject;
import Views.Styles.MapEditorStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by ISmir on 27.10.2016.
 */
public class MapObjectView extends JPanel
{
    BufferedImage objectImage;
    BufferedImage wrapperImage;

    public MapObjectView(BufferedImage objectImage, MapEditorStyle style)
    {
        this.objectImage = objectImage;
        this.wrapperImage = style.getMapObjectWrapper();
        //setMinimumSize(new Dimension(objectImage.getWidth(), objectImage.getHeight()));
    }

    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        int heightMargin = (wrapperImage.getHeight() - objectImage.getHeight()) / 2;
        int widthMargin = (wrapperImage.getWidth() - objectImage.getWidth()) / 2;
        g2d.drawImage(wrapperImage, 0, 0, null);
        g2d.drawImage(objectImage, widthMargin, heightMargin, null);
    }
}
