package Core.GameObjects;

import Core.MapObjects.DynamicMapObjects.BigObjectCell;

import java.util.Iterator;

/**
 * Created by Владимир on 27.11.2016.
 */
public class BigObjectCellIterator implements Iterator<BigObjectCell>
{
    BigObjectCell curCell;

    BigObjectCellIterator(BigObjectCell cell)
    {
        curCell = cell;
    }

    @Override
    public boolean hasNext()
    {
        return curCell != null;
    }

    @Override
    public BigObjectCell next()
    {
        BigObjectCell next = curCell;
        curCell = curCell.previous;
        return next;
    }
}
