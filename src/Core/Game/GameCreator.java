package Core.Game;

import Core.MapObjects.DynamicMapObjects.SnakeCell;
import Core.MapObjects.MapObject;
import Core.MapObjects.StaticMapObjects.Berries.Blueberry;
import Core.MapObjects.StaticMapObjects.Berries.Strawberry;
import Core.MapObjects.StaticMapObjects.EmptyCell;
import Core.MapObjects.StaticMapObjects.SandGlass;
import Core.MapObjects.StaticMapObjects.Wall;
import Core.Snake.Snake;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

/**
 * Created by Владимир on 19.09.2016.
 */

public class GameCreator
{
    private MapObject[][] map;

    public GameCreator()
    {
        map = new MapObject[0][0];
    }

    private static MapObject[][] loadMap(String fileName)
    {
        throw new NotImplementedException();
    }

    public Game createGame(int snakeX, int snakeY, int snakeLength)
    {
        snakeX += 1;
        snakeY += 1;
        if (!isCellInMap(snakeX, snakeY) ||
                map[snakeX][snakeY].getClass() != EmptyCell.class)
            throw new IllegalArgumentException("Invalid snake position!");
        SnakeCell cell = new SnakeCell(null);
        Snake snake = new Snake(snakeLength, cell);
        map[snakeX][snakeY] = cell;
        return new Game(map, snake);
    }

    private boolean isCellInMap(int x, int y)
    {
        return 0 < x && x + 1 < map.length &&
                0 < y && y + 1 < map[0].length;
    }

    public void setMapSize(int width, int height)
    {
        map = new MapObject[height + 2][width + 2];
        for (int i = 0; i < map.length; ++i)
            for(int j = 0; j < map[0].length; ++j)
                map[i][j] = new EmptyCell();
        for (int i = 0; i < map.length; ++i)
        {
            map[i][0] = new Wall();
            map[i][map[0].length - 1] = new Wall();
        }
        for (int i = 1; i + 1 < map[0].length; ++i)
        {
            map[0][i] = new Wall();
            map[map.length - 1][i] = new Wall();
        }
    }

    public void resizeMap(int newWidth, int newHeight)
    {
        MapObject[][] prevMap = map;
        setMapSize(newWidth, newHeight);
        for(int i = 0; i < min(prevMap.length, map.length) - 2; ++i)
            for(int j = 0; j < min(prevMap[0].length, map[0].length) - 2; ++j)
                placeMapObject(i, j, prevMap[i + 1][j + 1]);
    }

    private void placeMapObject(int x, int y, MapObject mapObject)
    {
        if (!isCellInMap(x + 1, y + 1))
            throw new IllegalArgumentException("No such cell in the map.");
        map[x + 1][y + 1] = mapObject;
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

    // LU - left up angle of rectangle
    // RD - right down angle of rectangle
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
