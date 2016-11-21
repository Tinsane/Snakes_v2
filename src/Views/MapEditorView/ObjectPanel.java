package Views.MapEditorView;

import Core.Game.GameCreatorWrapper;
import Core.MapObjects.MapObject;
import Views.Styles.MapEditorStyle;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static Core.Game.GameCreatorWrapper.Pointer.MapPosition;

/**
 * Created by ISmir on 27.10.2016.
 */
public class ObjectPanel extends JPanel
{
    private final Color BACKGROUND_COLOR = new Color(239, 228, 176);

    GameCreatorWrapper gameCreator;
    MapEditorStyle style;
    Vector<MapObjectView> objectViews = new Vector<>();

    ObjectPanel(MapEditorStyle style, GameCreatorWrapper gameCreator, boolean doubleBuffered) throws IOException
    {
        super(doubleBuffered);
        setBackground(BACKGROUND_COLOR);
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
        objectViews.forEach(MapObjectView::unselect);
        MapObjectView selected = objectViews.get(gameCreator.mapObjectIndex);
        if (gameCreator.pointer == MapPosition)
            selected.select();
        else
            selected.focus();
        repaint();
    }
}
