package Views.MapEditorView;

import Core.Game.GameAlike;
import Core.Game.GameCreatorWrapper;
import Core.MapObjects.MapObjectVisitor;
import Core.Utils.IntPair;
import Views.GameView.GameViewCanvas;
import Views.Styles.GameStyle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by ISmir on 23.10.2016.
 */
public class MapEditorCanvas extends JPanel
{
    private final BufferedImage distinguishImage;
    private GameAlike game;
    private GameStyle style;
    private double goneTurnPart;

    MapEditorCanvas(GameAlike game, GameStyle style, boolean doubleBuffered) throws IOException
    {
        super(doubleBuffered);
        this.game = game;
        this.style = style;
        distinguishImage = ImageIO.read(getClass().getResource("distinguish.png"));
    }

    public void repaint(double goneTurnPart)
    {
        this.goneTurnPart = goneTurnPart;
        repaint();
    }

    private void drawImage(Graphics2D graphics, GameStyle style, BufferedImage image, int x, int y)
    {
        graphics.drawImage(image, x * style.getTileSize(), y * style.getTileSize(), null);
    }

    private void distinguish(Graphics graphics, IntPair location)
    {
        drawImage((Graphics2D)graphics, style, distinguishImage, location.x, location.y);
    }

    @Override
    public void paint(Graphics g)
    {
        MapObjectVisitor drawer = style.CreateDrawer((Graphics2D) g, game, goneTurnPart);
        drawer.visitAll();
        GameCreatorWrapper gameCreator = (GameCreatorWrapper)game;
        distinguish(g, gameCreator.mapPosition);
    }
}
