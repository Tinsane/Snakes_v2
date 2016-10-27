package Views.Styles.Default;

import Views.Styles.Default.DefaultStyle;
import Views.Styles.MapEditorStyle;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by ISmir on 27.10.2016.
 */
public class MapEditorDefaultStyle extends DefaultStyle implements MapEditorStyle
{
    public final BufferedImage chosenLocationImage;
    public final BufferedImage activeLocationImage;

    public MapEditorDefaultStyle() throws IOException
    {
        chosenLocationImage = loadImage("chosen.png");
        activeLocationImage = loadImage("active.png");
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
    public BufferedImage getChosenLocationImage()
    {
        return chosenLocationImage;
    }

    @Override
    public BufferedImage getActiveLocationImage()
    {
        return activeLocationImage;
    }
}
