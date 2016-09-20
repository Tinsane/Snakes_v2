import java.util.Iterator;

/**
 * Created by Владимир on 20.09.2016.
 */
public class SnakeCellIterator implements Iterator<SnakeCell>
{
    SnakeCell curCell;

    SnakeCellIterator(SnakeCell cell)
    {
        curCell = cell;
    }

    @Override
    public boolean hasNext()
    {
        return curCell != null;
    }

    @Override
    public SnakeCell next()
    {
        SnakeCell next = curCell;
        curCell = curCell.previous;
        return next;
    }
}
