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

    void berryProcessCollision(Berry berry, Game game);
    void snakeCellProcessCollision(SnakeCell snakeCell, Game game);
    void wallProcessCollision(Wall wall, Game game);
    void sandGlassProcessCollision(SandGlass sandGlass, Game game);
    void emptyCellProcessCollision(EmptyCell emptyCell, Game game);
}