package Core.MapObjects.DynamicMapObjects;

import Core.Game.Game;
import Core.MapObjects.MapObject;
import Core.MapObjects.StaticMapObjects.Berries.Berry;
import Core.MapObjects.StaticMapObjects.SandGlass;
import Core.MapObjects.StaticMapObjects.Wall;
import Core.GameObjects.Snake.Snake;
import Core.Utils.IntPair;
import Core.Utils.VelocityVector;
import Core.MapObjects.MapObjectVisitor;

/**
 * Created by Владимир on 16.09.2016.
 */
public class SnakeCell extends DynamicMapObject
{
    public SnakeCell previous;

    public SnakeCell(SnakeCell previous)
    {
        this(previous, VelocityVector.zero);
    }

    public SnakeCell(SnakeCell previous, VelocityVector velocity)
    {
        this.previous = previous;
        this.setVelocity(velocity);
    }

    public static IntPair getPreviousCoordinates(MapObject[][] map, IntPair coordinates)
    {
        SnakeCell currentCell = (SnakeCell) map[coordinates.x][coordinates.y];
        IntPair[] diffs = new IntPair[]{
                new IntPair(1, 0),
                new IntPair(-1, 0),
                new IntPair(0, 1),
                new IntPair(0, -1)
        };
        for (IntPair diff : diffs)
        {
            IntPair newPair = coordinates.getAdded(diff);
            if (map[newPair.x][newPair.y] == currentCell.previous)
                return newPair;
        }
        return null;
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
    public void processCollision(MapObject mapObject, Game game)
    {
        mapObject.processCollision(this, game);
    }

    @Override
    public void processCollision(Berry berry, Game game)
    {
        // probably need to check if cell is head
        berry.setIsDestructed(true);
        Snake.getSnakeOwner(game, this).extend(berry.getSatisfactionCoefficient());
    }

    @Override
    public void processCollision(SnakeCell snakeCell, Game game)
    {
        // let's just kill the snake
        Snake snake = Snake.getSnakeOwner(game, this);
        if (this == snake.head)
            snake.setIsDestructed(true);
        else
            snakeCell.processCollision(this, game);
    }

    @Override
    public void processCollision(Wall wall, Game game)
    {
        Snake.getSnakeOwner(game, this).setIsDestructed(true);
    }

    @Override
    public void processCollision(SandGlass sandGlass, Game game)
    {
        game.rollback(sandGlass.getRollbackTurnsNumber());
        sandGlass.setIsDestructed(true);
    }

    @Override
    public void acceptVisitor(MapObjectVisitor visitor)
    {
        visitor.visit(this);
    }
}
