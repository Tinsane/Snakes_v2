package Views.MapEditorView;

import Core.Game.GameCreatorWrapper;
import Views.Styles.MapEditorStyle;

import java.awt.*;
import java.io.IOException;

import static Core.Game.GameCreatorWrapper.Pointer.MapObjectType;

/**
 * Created by ISmir on 23.10.2016.
 */
public class MapEditorCanvas extends MapEditorAbstractPanel
{
    MapEditorCanvas(GameCreatorWrapper game, MapEditorStyle style, boolean doubleBuffered) throws IOException
    {
        super(game, style, doubleBuffered);
    }

    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        style.CreateDrawer(g2d, gameCreator, 0).draw();
        highlight(g2d, gameCreator.mapPosition, gameCreator.pointer == MapObjectType);
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(gameCreator.getWidth() * style.getTileSize(),
                             gameCreator.getHeight() * style.getTileSize());
    }
}
