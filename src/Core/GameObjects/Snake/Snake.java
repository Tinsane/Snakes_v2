package Core.GameObjects.Snake;

import Core.Game.Game;
import Core.Game.GameAlike;
import Core.GameObjects.GameObject;
import Core.Game.GameUpdaters.GameUpdater;
import Core.MapObjects.DynamicMapObjects.SnakeCell;
import Core.MapObjects.MapObject;
import Core.Utils.IntPair;
import Core.Utils.VelocityVector;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Created by Владимир on 16.09.2016.
 */
public class Snake implements Iterable<SnakeCell>, Serializable, GameObject
{
    public SnakeCell head;
    public SnakeCell tail;
    private boolean isDestructed = false;

    public Snake(int length)
    {
        this(length, new SnakeCell(null));
    }

    public Snake(int length, SnakeCell head)
    {
        if (length <= 0)
            throw new IllegalArgumentException();
        tail = this.head = head;
        extend(length - 1);
    }

    public void setVelocity(VelocityVector vector)
    {
        head.setVelocity(vector);
    }

    public int getLength()
    {
        int length = 0;
        for (SnakeCell ignored : this)
            ++length;
        return length;
    }

    public void incLength()
    {
        tail.previous = new SnakeCell(null);
        tail = tail.previous;
    }

    public void extend(int length)
    {
        if (length < 0)
            throw new IllegalArgumentException();
        for (int i = 0; i < length; ++i)
            incLength();
    }

    public boolean getIsDestructed()
    {
        return isDestructed;
    }

    private void moveSnakeCell(GameUpdater updater, SnakeCell cell, IntPair cellPosition)
    {
        if (cell.previous != null)
        {
            IntPair previousPosition = SnakeCell.getPreviousCoordinates(updater.getCurrentMap(), cellPosition);
            if (previousPosition == null)
                updater.placeObject(cell.previous, cellPosition);
            else
                moveSnakeCell(updater, cell.previous, previousPosition);
            cell.previous.setVelocity(cell.getVelocity());
        }
        updater.moveObject(cellPosition);
    }

    @Override
    public void updatePosition(GameUpdater updater)
    {
        moveSnakeCell(updater, head, head.getCoordinates(updater.getCurrentMap()));
    }

    public void setIsDestructed(boolean isDestructed)
    {
        this.isDestructed = isDestructed;
        for (SnakeCell cell : this)
            cell.setIsDestructed(isDestructed);
    }

    @Override
    public Iterator<SnakeCell> iterator()
    {
        return new SnakeCellIterator(head);
    }

    @Override
    public boolean contains(MapObject mapObject)
    {
        if (!(mapObject instanceof SnakeCell))
            return false;
        SnakeCell snakeCell = (SnakeCell)mapObject;
        for (SnakeCell cell : this)
            if (cell == snakeCell)
                return true;
        return false;
    }

    public static Snake getSnake(GameAlike game)
    {
        return getSnake(game, 0);
    }

    public static Snake getSnake(GameAlike game, int index)
    {
        return (Snake)game.getGameObject(Snake.class, index);
    }

    public static Snake getSnakeOwner(GameAlike game, SnakeCell cell)
    {
        return (Snake)game.getOwner(cell);
    }
}
