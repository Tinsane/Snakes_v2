package Views.MapEditorView;

import Core.MapObjects.DynamicMapObjects.SnakeCell;
import Core.MapObjects.MapObject;
import Core.MapObjects.MapObjectVisitor;
import Core.MapObjects.StaticMapObjects.Berries.Blueberry;
import Core.MapObjects.StaticMapObjects.Berries.Strawberry;
import Core.MapObjects.StaticMapObjects.EmptyCell;
import Core.MapObjects.StaticMapObjects.SandGlass;
import Core.MapObjects.StaticMapObjects.Wall;
import Views.Styles.MapEditorStyle;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.image.BufferedImage;

/**
 * Created by ISmir on 05.11.2016.
 */
// note: This is thread unsafe
public class MapObjectViewBuilder implements MapObjectVisitor
{
    private BufferedImage image;
    private MapEditorStyle style;

    public MapObjectViewBuilder(MapEditorStyle style)
    {
        this.style = style;
    }

    @Override
    public void visit(EmptyCell emptyCell)
    {
        image = style.getEmptyCellImage();
    }

    @Override
    public void visit(Wall wall)
    {
        image = style.getWallImage();
    }

    @Override
    public void visit(SandGlass sandGlass)
    {
        image = style.getSandGlassImage();
    }

    @Override
    public void visit(Strawberry strawberry)
    {
        image = style.getStrawberryImage();
    }

    @Override
    public void visit(Blueberry blueberry)
    {
        image = style.getBlueberryImage();
    }

    @Override
    public void visit(SnakeCell snakeCell)
    {
        throw new NotImplementedException();
    }

    public MapObjectView createView(MapObject object)
    {
        object.acceptVisitor(this);
        return new MapObjectView(image, style);
    }
}
