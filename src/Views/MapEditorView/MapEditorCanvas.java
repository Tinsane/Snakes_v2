package Views.MapEditorView;

import Core.Game.GameCreatorWrapper;
import Core.Utils.IntPair;
import Views.Styles.GameStyle;
import Views.Styles.MapEditorStyle;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static Core.Game.GameCreatorWrapper.Pointer.MapPosition;

/**
 * Created by ISmir on 23.10.2016.
 */
public class MapEditorCanvas extends JPanel
{
    private GameCreatorWrapper gameCreator;
    private MapEditorStyle style;

    MapEditorCanvas(GameCreatorWrapper gameCreator, MapEditorStyle style, boolean doubleBuffered) throws IOException
    {
        super(doubleBuffered);
        this.gameCreator = gameCreator;
        this.style = style;
    }

    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        style.CreateDrawer(g2d, gameCreator, 0).draw();
        highlight(g2d, gameCreator.getMapPosition(), gameCreator.pointer == MapPosition);
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(
                gameCreator.getWidth() * style.getTileSize(),
                gameCreator.getHeight() * style.getTileSize());
    }

    private void drawImage(Graphics2D graphics, GameStyle style, BufferedImage image, int x, int y)
    {
        graphics.drawImage(image, x * style.getTileSize(), y * style.getTileSize(), null);
    }

    private void highlight(Graphics2D graphics, IntPair location, boolean active)
    {
        BufferedImage distinguishImage = active ? style.getFocusedLocationImage() : style.getSelectedLocationImage();
        drawImage(graphics, style, distinguishImage, location.x, location.y);
    }
}
