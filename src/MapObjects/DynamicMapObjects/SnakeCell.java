package MapObjects.DynamicMapObjects;

import Game.Game;
import MapObjects.MapObject;
import MapObjects.StaticMapObjects.Berries.Berry;
import MapObjects.StaticMapObjects.SandGlass;
import MapObjects.StaticMapObjects.Wall;
import Utils.IntPair;

/**
 * Created by Владимир on 16.09.2016.
 */
public class SnakeCell extends DynamicMapObject
{
    public SnakeCell previous;

    public SnakeCell(SnakeCell previous)
    {
        this.previous = previous;
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
    public void processCollision(MapObject visitor, Game game)
    {
        visitor.snakeCellProcessCollision(this, game);
    }

    @Override
    public void berryProcessCollision(Berry berry, Game game)
    {
        // probably need to check if cell is head
        berry.setIsDestructed(true);
        game.snake.extend(berry.getSatisfactionCoefficient());
    }

    @Override
    public void snakeCellProcessCollision(SnakeCell snakeCell, Game game)
    {
        // let's just kill the snake
        game.snake.setIsDestructed(true);
    }

    @Override
    public void wallProcessCollision(Wall wall, Game game)
    {
        game.snake.setIsDestructed(true);
    }

    @Override
    public void sandGlassProcessCollision(SandGlass sandGlass, Game game)
    {
        game.rollback(sandGlass.getRollbackTurnsNumber());
        sandGlass.setIsDestructed(true);
    }
}
