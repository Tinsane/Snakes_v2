package Core.MapObjects;

import Core.MapObjects.DynamicMapObjects.SnakeCell;
import Core.MapObjects.StaticMapObjects.Berries.Berry;
import Core.MapObjects.StaticMapObjects.EmptyCell;
import Core.MapObjects.StaticMapObjects.SandGlass;
import Core.MapObjects.StaticMapObjects.Wall;
import Core.Utils.VelocityVector;
import Core.Game.Game;

/**
 * Created by Владимир on 16.09.2016.
 */

public interface MapObject
{
    VelocityVector getVelocity();

    boolean getIsDestructed();

    void processCollision(MapObject mapObject, Game game);

    void processCollision(Berry berry, Game game);
    void processCollision(SnakeCell snakeCell, Game game);
    void processCollision(Wall wall, Game game);
    void processCollision(SandGlass sandGlass, Game game);
    void processCollision(EmptyCell emptyCell, Game game);
    void acceptVisitor(MapObjectVisitor visitor);
}