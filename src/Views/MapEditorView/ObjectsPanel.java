package Views.MapEditorView;

import Core.Game.GameCreator;
import Core.Game.GameCreatorWrapper;
import Core.MapObjects.MapObjectVisitor;
import Views.Styles.GameStyle;
import Views.Styles.MapEditorStyle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static Core.Game.GameCreatorWrapper.Pointer.MapObjectType;

/**
 * Created by ISmir on 27.10.2016.
 */
public class ObjectsPanel extends MapEditorPanel
{
    ObjectsPanel(GameCreatorWrapper game, MapEditorStyle style, boolean doubleBuffered) throws IOException
    {
        super(game, style, doubleBuffered);
    }

    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;

    }
}
