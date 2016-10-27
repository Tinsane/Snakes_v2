package Views.Styles;

import java.awt.image.BufferedImage;

/**
 * Created by ISmir on 27.10.2016.
 */
public interface MapEditorStyle extends GameStyle
{
    BufferedImage getChosenLocationImage();
    BufferedImage getActiveLocationImage();
}
