package Views.MapEditorView;

import Core.Game.GameCreatorWrapper;
import Views.Styles.MapEditorStyle;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by ISmir on 27.10.2016.
 */
public class ObjectPanel extends JPanel
{
    ObjectPanel(MapEditorStyle style, boolean doubleBuffered) throws IOException
    {
        super(doubleBuffered);
        setBackground(Color.getHSBColor(24, 80, 70));
        //setLayout(new FlowLayout(FlowLayout.LEFT, 5, 10));
        setLayout(new GridLayout(1, 4));
        setSize(400, style.getTileSize());
        add(new MapObjectView(style.getEmptyCellImage())); // TODO: solve problem somehow
        add(new MapObjectView(style.getWallImage())); // TODO: we use here some order and in GameCreatorWrapper some order
        add(new MapObjectView(style.getStrawberryImage())); // TODO: how to make that orders coincide forcibly
        add(new MapObjectView(style.getBlueberryImage()));
    }
}
