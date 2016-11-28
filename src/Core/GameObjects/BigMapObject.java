package Core.GameObjects;

import Core.Game.GameUpdaters.GameUpdater;
import Core.MapObjects.DynamicMapObjects.BigObjectCell;
import Core.MapObjects.MapObject;
import Core.Utils.IntPair;

import java.util.Iterator;

/**
 * Created by Владимир on 27.11.2016.
 */
public abstract class BigMapObject implements GameObject, Iterable<BigObjectCell>
{
    public BigObjectCell head;
    public BigObjectCell tail;
    private boolean isDestructed = false;

    public BigMapObject(int length, BigObjectCell head)
    {
        if (length <= 0)
            throw new IllegalArgumentException();
        tail = this.head = head;
        extend(length - 1);
    }

    public abstract void incLength();

    public int getLength()
    {
        int length = 0;
        for (BigObjectCell ignored : this)
            ++length;
        return length;
    }

    public void extend(int length)
    {
        if (length < 0)
            throw new IllegalArgumentException();
        for (int i = 0; i < length; ++i)
            incLength();
    }

    public void setIsDestructed(boolean isDestructed)
    {
        this.isDestructed = isDestructed;
        for (BigObjectCell cell : this)
            cell.setIsDestructed(isDestructed);
    }

    private void moveFromHead(GameUpdater updater, BigObjectCell cell, IntPair cellPosition)
    {
        if (cell.previous != null)
        {
            IntPair previousPosition = cell.getPreviousCoordinates(updater.getCurrentMap(), cellPosition);
            if (previousPosition == null)
                updater.placeObject(cell.previous, cellPosition);
            else
                moveFromHead(updater, cell.previous, previousPosition);
            cell.previous.setVelocity(cell.getVelocity());
        }
        updater.moveObject(cellPosition);
    }

    @Override
    public void updatePosition(GameUpdater updater)
    {
        moveFromHead(updater, head, head.getCoordinates(updater.getCurrentMap()));
    }

    @Override
    public boolean contains(MapObject mapObject)
    {
        if (!(mapObject instanceof BigObjectCell))
            return false;
        BigObjectCell bigObjectCell = (BigObjectCell)mapObject;
        for (BigObjectCell cell : this)
            if (cell == bigObjectCell)
                return true;
        return false;
    }

    @Override
    public boolean getIsDestructed()
    {
        return isDestructed;
    }

    @Override
    public Iterator<BigObjectCell> iterator()
    {
        return new BigObjectCellIterator(head);
    }
}
