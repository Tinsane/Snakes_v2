package Views.MapEditorView;

import Core.Game.Game;
import Core.Game.GameCreatorWrapper;
import Core.MapObjects.MapObject;
import Views.Styles.MapEditorStyle;
import com.sun.javafx.UnmodifiableArrayList;
import com.sun.javafx.collections.ImmutableObservableList;
import com.sun.javafx.geom.Dimension2D;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by ISmir on 27.10.2016.
 */
public class ObjectPanel extends JPanel
{
    GameCreatorWrapper gameCreator;
    MapEditorStyle style;

    ObjectPanel(MapEditorStyle style, GameCreatorWrapper gameCreator, boolean doubleBuffered) throws IOException
    {
        super(doubleBuffered);
        setBackground(new Color(239, 228, 176));
        setLayout(new GridLayout(1, gameCreator.getMapObjectsCount()));
        addObjectsOnPanel(style, gameCreator);
        this.gameCreator = gameCreator;
        this.style = style;
    }

    private void addObjectsOnPanel(MapEditorStyle style, GameCreatorWrapper gameCreator)
    {
        MapObjectViewBuilder viewBuilder = new MapObjectViewBuilder(style);
        UnmodifiableArrayList<MapObject> mapObjects = gameCreator.getMapObjects();
        for (MapObject mapObject : mapObjects)
            add(viewBuilder.createView(mapObject));
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(gameCreator.getMapObjectsCount() * style.getMapObjectWrapper().getWidth(),
                             style.getMapObjectWrapper().getHeight());
    }
}
