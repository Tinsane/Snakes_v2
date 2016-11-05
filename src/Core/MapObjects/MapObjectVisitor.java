package Core.MapObjects;

import Core.MapObjects.DynamicMapObjects.SnakeCell;
import Core.MapObjects.StaticMapObjects.Berries.Blueberry;
import Core.MapObjects.StaticMapObjects.Berries.Strawberry;
import Core.MapObjects.StaticMapObjects.EmptyCell;
import Core.MapObjects.StaticMapObjects.SandGlass;
import Core.MapObjects.StaticMapObjects.Wall;

/**
 * Created by ISmir on 08.10.2016.
 */
public interface MapObjectVisitor
{
    void visit(EmptyCell emptyCell);

    void visit(Wall wall);

    void visit(SandGlass sandGlass);

    void visit(Strawberry strawberry);

    void visit(Blueberry blueberry);

    void visit(SnakeCell snakeCell);
}
