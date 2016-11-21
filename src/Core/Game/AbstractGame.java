package Core.Game;

import Core.MapObjects.DynamicMapObjects.SnakeCell;
import Core.Snake.Snake;

import java.util.ArrayList;

/**
 * Created by Владимир on 21.11.2016.
 */
public abstract class AbstractGame implements GameAlike
{

    public int getWidth() { return getCurrentMap().length; }

    public int getHeight() { return getCurrentMap()[0].length; }

    public int getOwnerIndex(SnakeCell cell)
    {
        ArrayList<Snake> snakes = getSnakes();
        for(int i = 0; i < snakes.size(); ++i)
            if (snakes.get(i).contains(cell))
                return i;
        throw new IllegalArgumentException("Abeyant snake cell.");
    }

    public Snake getOwner(SnakeCell cell)
    {
        return getSnakes().get(getOwnerIndex(cell));
    }
}
