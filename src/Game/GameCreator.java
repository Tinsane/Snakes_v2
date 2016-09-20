package Game;

import MapObjects.DynamicMapObjects.*;
import MapObjects.*;
import MapObjects.StaticMapObjects.*;
import MapObjects.StaticMapObjects.Berries.*;
import Snake.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Владимир on 19.09.2016.
 */

public class GameCreator
{
    private MapObject[][] map;

    GameCreator()
    {
        map = new MapObject[0][0];
    }

    private static MapObject[][] loadMap(String fileName)
    {
        throw new NotImplementedException();
    }

    public Game createGame(int snakeX, int snakeY, int snakeLength)
    {
        if (!isCellInMap(snakeX, snakeY) ||
                map[snakeX][snakeY] != null)
            throw new IllegalArgumentException("Invalid snake position!");
        SnakeCell cell = new SnakeCell(null);
        Snake snake = new Snake(snakeLength, cell);
        map[snakeX][snakeY] = cell;
        return new Game(map, snake);
    }

    private boolean isCellInMap(int x, int y)
    {
        return 0 <= x && x < map.length &&
                0 <= y && y < map[0].length;
    }

    public void setMapSize(int width, int height)
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

    // LU - left down angle of rectangle
    // RD - right up angle of rectangle
    public void placeMapObjectsInRectangle(int xLU, int yLU, int xRD, int yRD, MapObject mapObject)
    {
        for (int x = xLU; x <= xRD; ++x)
            for (int y = yLU; y <= yRD; ++y)
                placeMapObject(x, y, mapObject);
    }

    public void placeMapObjectsInLineX(int x, int yU, int yD, MapObject mapObject)
    {
        placeMapObjectsInRectangle(x, yU, x, yD, mapObject);
    }

    public void placeMapObjectsInLineY(int xL, int xR, int y, MapObject mapObject)
    {
        placeMapObjectsInRectangle(xL, y, xR, y, mapObject);
    }
}
