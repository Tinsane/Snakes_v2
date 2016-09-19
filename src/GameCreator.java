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

    private MapObject[][] map;

    GameCreator()
    {
        map = new MapObject[0][0];
    }

    public Game createGame(int snakeX, int snakeY)
    {
        if (!isCellInMap(snakeX, snakeY) ||
                map[snakeX][snakeY] != null)
            throw new IllegalArgumentException("Invalid snake position!");
        SnakeCell cell = new SnakeCell(null);
        map[snakeX][snakeY] = cell;
        Snake snake = new Snake(cell, cell);
        return new Game(map, snake);
    }

    public boolean isCellInMap(int x, int y)
    {
        return 0 <= x && x < map.length &&
                0 <= y && y < map[0].length;
    }

    public void createMap(int width, int height)
    {
        map = new MapObject[height][width];
    }

    private void placeMapObject(int x, int y, MapObject mapObject)
    {
        if (!isCellInMap(x, y))
            throw new IllegalArgumentException("No such cell in the map.");
        map[x][y] = mapObject;
    }

    public void placeWall(int x, int y)
    {
        placeMapObject(x, y, new Wall());
    }

    public void placeBlueberry(int x, int y)
    {
        placeMapObject(x, y, new Blueberry());
    }

    public void placeStrawberry(int x, int y)
    {
        placeMapObject(x, y, new Strawberry());
    }

    public void placeSandGlass(int x, int y, int rollbackTurnsCount)
    {
        placeMapObject(x, y, new SandGlass(rollbackTurnsCount));
    }
}
