package Core.MapObjects;

import Core.Game.Game;
import Core.MapObjects.StaticMapObjects.EmptyCell;
import Core.Utils.IntPair;

import java.io.Serializable;

/**
 * Created by ISmir on 17.09.2016.
 */
public abstract class BaseMapObject implements MapObject, Serializable
{
    public boolean isDestructed = false;

    @Override
    public boolean getIsDestructed()
    {
        return isDestructed;
    }

    public void setIsDestructed(boolean isDestructed)
    {
        this.isDestructed = isDestructed;
    }

    public IntPair getCoordinates(MapObject[][] map)
    {
        for (int x = 0; x < map.length; ++x)
            for (int y = 0; y < map[0].length; ++y)
                if (map[x][y] == this)
                    return new IntPair(x, y);
        throw new IllegalArgumentException();
    }

    @Override
    public void processCollision(EmptyCell emptyCell, Game game)
    {
        emptyCell.setIsDestructed(true);
    }
}
