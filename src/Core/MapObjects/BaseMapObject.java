package Core.MapObjects;

import Core.Game.Game;
import Core.MapObjects.StaticMapObjects.EmptyCell;

/**
 * Created by ISmir on 17.09.2016.
 */
public abstract class BaseMapObject implements MapObject
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

    @Override
    public void emptyCellProcessCollision(EmptyCell emptyCell, Game game)
    {
        emptyCell.setIsDestructed(true);
    }
}
