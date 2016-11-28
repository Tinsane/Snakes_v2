package Core.GameObjects;

import Core.Game.GameAlike;
import Core.MapObjects.DynamicMapObjects.SnakeCell;
import Core.Utils.VelocityVector;

/**
 * Created by Владимир on 16.09.2016.
 */
public class Snake extends BigMapObject
{
    public Snake(int length)
    {
        this(length, new SnakeCell(null));
    }

    public Snake(int length, SnakeCell head)
    {
        super(length, head);
    }

    @Override
    public void incLength()
    {
        SnakeCell cell = new SnakeCell();
        tail.previous = cell;
        tail = cell;
    }

    public void setVelocity(VelocityVector vector)
    {
        head.setVelocity(vector);
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
