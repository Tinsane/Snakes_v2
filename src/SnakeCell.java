import javafx.util.Pair;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Владимир on 16.09.2016.
 */
public class SnakeCell extends DynamicMapObject
{
    private SnakeCell previous;

    SnakeCell(SnakeCell previous)
    {
        this.previous = previous;
    }

    public SnakeCell getPrevious()
    {
        return previous;
    }

    public Pair<Integer, Integer> getCoordinates(MapObject[][] map)
    {
        for (int x = 0; x < map.length; ++x)
            for (int y = 0; y < map[0].length; ++y)
                if (map[x][y] == this)
                    return new Pair<>(x, y);
        throw new IllegalArgumentException();
    }

    public static Pair<Integer, Integer> getPreviousCoordinates(MapObject[][] map, int currentX, int currentY)
    {
        SnakeCell currentCell = (SnakeCell)map[currentX][currentY];
        int[] dx = new int[]{1, -1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};
        for (int i = 0; i < 4; ++i)
        {
            int newX = currentX + dx[i];
            int newY = currentY + dy[i];
            if (map[newX][newY] == currentCell.getPrevious())
                return new Pair<>(newX, newY);
        }
        return null;
    }

    public void processCollision(SnakeCell snakeCell, Game game)
    {
        // let's just kill the snake
        game.snake.setIsDestructed(true);
    }

    public void processCollision(Berry berry, Game game)
    {
        // probably need to check if cell is head
        berry.setIsDestructed(true);
        game.snake.extend(berry.getSatisfactionCoefficient());
    }

    public void processCollision(SandGlass sandGlass, Game game)
    {
        game.rollback(sandGlass.rollbackTurnsNumber);
        sandGlass.setIsDestructed(true);
    }

    public void processCollision(Wall wall, Game game)
    {
        game.snake.setIsDestructed(true);
    }
}
