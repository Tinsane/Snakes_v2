package Core.Game;

import Core.MapObjects.DynamicMapObjects.SnakeCell;
import Core.MapObjects.MapObject;
import Core.Snake.Snake;

import java.util.ArrayList;

/**
 * Created by ISmir on 23.10.2016.
 */
public interface GameAlike
{
    MapObject[][] getCurrentMap();

    ArrayList<Snake> getSnakes();
    Snake getOwner(SnakeCell cell);
    int getOwnerIndex(SnakeCell cell);

    int getWidth();
    int getHeight();
}
