package Views.MapEditorView;

import Core.Game.GameCreatorWrapper;
import Core.Utils.IntPair;
import Views.Styles.GameStyle;
import Views.Styles.MapEditorStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by ISmir on 27.10.2016.
 */
public abstract class MapEditorPanel extends JPanel
{
    protected GameCreatorWrapper gameCreator;
    protected MapEditorStyle style;

    MapEditorPanel(GameCreatorWrapper gameCreator, MapEditorStyle style, boolean doubleBuffered) throws IOException
    {
        super(doubleBuffered);
        this.gameCreator = gameCreator;
        this.style = style;
    }

    protected void drawImage(Graphics2D graphics, GameStyle style, BufferedImage image, int x, int y)
    {
        graphics.drawImage(image, x * style.getTileSize(), y * style.getTileSize(), null);
    }

    protected void distinguish(Graphics2D graphics, IntPair location, boolean active)
    {
        BufferedImage distinguishImage = active ? style.getActiveLocationImage() : style.getChosenLocationImage();
        drawImage(graphics, style, distinguishImage, location.x, location.y);
    }
}
