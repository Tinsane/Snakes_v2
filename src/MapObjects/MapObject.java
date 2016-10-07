package MapObjects;

import MapObjects.DynamicMapObjects.SnakeCell;
import MapObjects.StaticMapObjects.Berries.Berry;
import MapObjects.StaticMapObjects.SandGlass;
import MapObjects.StaticMapObjects.Wall;
import Utils.VelocityVector;
import Game.Game;

/**
 * Created by Владимир on 16.09.2016.
 */

public interface MapObject
{
    VelocityVector getVelocity();

    boolean getIsDestructed();

    void processCollision(MapObject visitor, Game game);

    void berryProcessCollision(Berry berry, Game game);
    void snakeCellProcessCollision(SnakeCell snakeCell, Game game);
    void wallProcessCollision(Wall wall, Game game);
    void sandGlassProcessCollision(SandGlass sandGlass, Game game);
}