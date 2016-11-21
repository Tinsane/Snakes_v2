package Views.Styles.Default;

import Views.Styles.Default.DefaultStyle;
import Views.Styles.MapEditorStyle;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by ISmir on 27.10.2016.
 */
public class MapEditorDefaultStyle extends DefaultStyle implements MapEditorStyle
{
    public final BufferedImage chosenLocationImage;
    public final BufferedImage activeLocationImage;
    public final BufferedImage mapObjectWrapperImage;
    public final BufferedImage absolutelyEmptyImage;

    public MapEditorDefaultStyle() throws IOException
    {
        chosenLocationImage = loadImage("selected.png");
        activeLocationImage = loadImage("focused.png");
        mapObjectWrapperImage = loadImage("map_object_wrapper.png");
        absolutelyEmptyImage = loadImage("absolutely_empty.png");
    }

    @Override
    public BufferedImage getWallImage()
    {
        return wallImage;
    }

    @Override
    public BufferedImage getEmptyCellImage()
    {
        return emptyCellImage;
    }

    @Override
    public BufferedImage getStrawberryImage()
    {
        return strawberryImage;
    }

    @Override
    public BufferedImage getBlueberryImage()
    {
        return blueberryImage;
    }

    @Override
    public BufferedImage getSandGlassImage()
    {
        throw new NotImplementedException();
    }

    @Override
    public BufferedImage getSelectedLocationImage()
    {
        return chosenLocationImage;
    }

    @Override
    public BufferedImage getFocusedLocationImage()
    {
        return activeLocationImage;
    }

    @Override
    public BufferedImage getMapObjectWrapper()
    {
        return mapObjectWrapperImage;
    }

    @Override
    public BufferedImage getAbsolutelyEmptyImage()
    {
        return absolutelyEmptyImage;
    }
}
