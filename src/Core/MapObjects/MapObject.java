package Core.MapObjects;

import Core.MapObjects.DynamicMapObjects.SnakeCell;
import Core.MapObjects.StaticMapObjects.Berries.Berry;
import Core.MapObjects.StaticMapObjects.SandGlass;
import Core.MapObjects.StaticMapObjects.Wall;
import Core.Utils.VelocityVector;
import Core.Game.Game;
import View.Drawable;

/**
 * Created by Владимир on 16.09.2016.
 */

public interface MapObject extends Drawable
{
    VelocityVector getVelocity();

    boolean getIsDestructed();

    void processCollision(MapObject visitor, Game game);

    void berryProcessCollision(Berry berry, Game game);
    void snakeCellProcessCollision(SnakeCell snakeCell, Game game);
    void wallProcessCollision(Wall wall, Game game);
    void sandGlassProcessCollision(SandGlass sandGlass, Game game);

}