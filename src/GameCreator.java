import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Владимир on 19.09.2016.
 */

public class GameCreator
{
    private static MapObject[][] loadMap(String fileName)
    {
        throw new NotImplementedException();
    }

    public static Game createGame(MapObject[][] map, Vector snakePosition)
    {
        if (!(0 <= snakePosition.x && snakePosition.x < map.length) ||
                !(0 <= snakePosition.y && snakePosition.y < map[0].length) ||
                map[snakePosition.x][snakePosition.y] != null)
            throw new IllegalArgumentException("Invalid snake position!");
        SnakeCell cell = new SnakeCell();
        map[snakePosition.x][snakePosition.y] = cell;
        Snake snake = new Snake(cell, cell);
        return new Game(map, snake);
    }
}