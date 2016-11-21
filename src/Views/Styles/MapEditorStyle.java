package Views.Styles;

import Core.Game.GameAlike;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by ISmir on 27.10.2016.
 */
public interface MapEditorStyle extends GameStyle
{
    BufferedImage getWallImage();
    BufferedImage getEmptyCellImage();
    BufferedImage getStrawberryImage();
    BufferedImage getBlueberryImage();
    BufferedImage getSandGlassImage();

    BufferedImage getSelectedLocationImage();
    BufferedImage getFocusedLocationImage();
    BufferedImage getMapObjectWrapper();
    BufferedImage getAbsolutelyEmptyImage();
}
