package Views.MapEditorView;

import Core.Game.GameCreator;
import Core.Game.GameCreatorWrapper;
import Core.MapObjects.MapObjectVisitor;
import Views.Styles.GameStyle;
import Views.Styles.MapEditorStyle;
import sun.plugin2.util.ColorUtil;
import sun.plugin2.util.ColorUtil.ColorRGB;

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
        setBackground(Color.getHSBColor(24, 80, 70));
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 10));
        add(new MapObjectPanel(style.getEmptyCellImage())); // TODO: solve problem somehow
        add(new MapObjectPanel(style.getWallImage())); // TODO: we use here some order and in GameCreatorWrapper some order
        add(new MapObjectPanel(style.getStrawberryImage())); // TODO: how to make that orders coincide forcibly
        add(new MapObjectPanel(style.getBlueberryImage()));
    }
}
