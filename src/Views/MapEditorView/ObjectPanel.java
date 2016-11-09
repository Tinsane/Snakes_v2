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
import java.util.*;
import java.util.List;

import static Core.Game.GameCreatorWrapper.Pointer.MapObjectType;
import static Core.Game.GameCreatorWrapper.Pointer.MapPosition;

/**
 * Created by ISmir on 27.10.2016.
 */
public class ObjectPanel extends JPanel
{
    GameCreatorWrapper gameCreator;
    MapEditorStyle style;
    Vector<MapObjectView> objectViews = new Vector<>();

    ObjectPanel(MapEditorStyle style, GameCreatorWrapper gameCreator, boolean doubleBuffered) throws IOException
    {
        super(doubleBuffered);
        // TODO: constant
        setBackground(new Color(239, 228, 176));
        setLayout(new GridLayout(1, gameCreator.getMapObjectsCount()));
        addObjectsOnPanel(style, gameCreator);
        this.gameCreator = gameCreator;
        this.style = style;
    }

    private void addObjectsOnPanel(MapEditorStyle style, GameCreatorWrapper gameCreator)
    {
        MapObjectViewBuilder viewBuilder = new MapObjectViewBuilder(style);
        List<MapObject> mapObjects = gameCreator.getMapObjects();
        for (MapObject mapObject : mapObjects)
        {
            MapObjectView currentObjectView = viewBuilder.createView(mapObject);
            add(currentObjectView);
            objectViews.add(currentObjectView);
        }
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(gameCreator.getMapObjectsCount() * style.getMapObjectWrapper().getWidth(),
                             style.getMapObjectWrapper().getHeight());
    }

    public void update()
    {
        objectViews.forEach(MapObjectView::setUnselected);
        MapObjectView selected = objectViews.get(gameCreator.mapObjectIndex);
        if (gameCreator.pointer == MapPosition)
            selected.setChosen();
        else
            selected.setActive();
        repaint();
    }
}
