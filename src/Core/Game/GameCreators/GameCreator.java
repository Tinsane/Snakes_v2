package Core.Game.GameCreators;

import Core.Game.AbstractGame;
import Core.Game.Game;
import Core.GameObjects.CatDog;
import Core.GameObjects.GameObject;
import Core.GameUpdatingSystem.GameUpdatingSystem;
import Core.MapObjects.DynamicMapObjects.SnakeCell;
import Core.MapObjects.MapObject;
import Core.MapObjects.StaticMapObjects.Berries.Blueberry;
import Core.MapObjects.StaticMapObjects.Berries.Strawberry;
import Core.MapObjects.StaticMapObjects.EmptyCell;
import Core.MapObjects.StaticMapObjects.SandGlass;
import Core.MapObjects.StaticMapObjects.Wall;
import Core.GameObjects.Snake;
import Core.Utils.IntPair;
import sun.plugin.dom.exception.InvalidStateException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.function.Supplier;

import static java.lang.Integer.min;

/**
 * Created by Владимир on 19.09.2016.
 */

public class GameCreator extends AbstractGame
{
    protected MapObject[][] map;
    protected ArrayList<GameObject> gameObjects = new ArrayList<>();
    private GameUpdatingSystem gameUpdatingSystem;

    public GameCreator(GameUpdatingSystem gameUpdatingSystem)
    {
        map = new MapObject[0][0];
        this.gameUpdatingSystem = gameUpdatingSystem;
    }

    private static MapObject[][] loadMap(String fileName)
    {
        throw new NotImplementedException();
    }

    public Game createGame()
    {
        if (gameObjects == null)
            throw new InvalidStateException("Snake is null");
        return new Game(map, gameObjects, gameUpdatingSystem);
    }

    protected boolean isCellInMap(int x, int y)
    {
        return 0 < x && x + 1 < map.length &&
                0 < y && y + 1 < map[0].length;
    }

    protected boolean isCellInMap(IntPair cell)
    {
        return isCellInMap(cell.x, cell.y);
    }

    public void setMapSize(int width, int height)
    {
        setMapSize(width, height, EmptyCell::new);
    }

    public void setMapSize(int width, int height, Supplier<MapObject> fillObjectBuilder)
    {
        map = new MapObject[width + 2][height + 2];
        for (int i = 0; i < map.length; ++i)
            for(int j = 0; j < map[0].length; ++j)
                map[i][j] = fillObjectBuilder.get();
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
        resizeMap(newWidth, newHeight, EmptyCell::new);
    }

    public void resizeMap(int newWidth, int newHeight, Supplier<MapObject> fillObjectBuilder)
    {
        // possible to make faster
        MapObject[][] prevMap = map;
        setMapSize(newWidth, newHeight, fillObjectBuilder);
        for(int i = 0; i < min(prevMap.length, map.length) - 2; ++i)
            for(int j = 0; j < min(prevMap[0].length, map[0].length) - 2; ++j)
                placeMapObject(i, j, prevMap[i + 1][j + 1]);
    }

    protected void placeMapObject(int x, int y, MapObject mapObject)
    {
        if (!isCellInMap(x + 1, y + 1))
            throw new IllegalArgumentException("No such cell in the map.");
        GameObject owner = getOwner(map[x + 1][y + 1]);
        if (owner != null)
            deleteGameObject(owner);
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

    public void placeSnake(int snakeX, int snakeY, int snakeLength)
    {
        if (snakeLength < 1)
            throw new IllegalArgumentException(String.format("Snake length should be positive. Given : %1$d", snakeLength));
        Snake snake = new Snake(snakeLength);
        placeMapObject(snakeX, snakeY, snake.head);
        addGameObject(snake);
    }

    public void placeCatDog(int catDogX, int catDogY, int catDogLength)
    {
        if (catDogLength < 2)
            throw new IllegalArgumentException(String.format("CatDog length should be positive. Given : %1$d", catDogLength));
        CatDog catDog = new CatDog(catDogLength);
        placeMapObject(catDogX, catDogY, catDog.head); // TODO for me: use getCat and getDog instead
        placeMapObject(catDogX + 1, catDogY, catDog.tail);
        addGameObject(catDog);
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

    private void addGameObject(GameObject gameObject)
    {
        this.gameObjects.add(gameObject);
    }

    private void deleteGameObject(GameObject gameObject)
    {
        for (int x = 0; x < getWidth() - 2; ++x)
            for (int y = 0; y < getHeight() - 2; ++y)
                if (gameObject.contains(map[x + 1][y + 1]))
                    map[x + 1][y + 1] = new EmptyCell();
    }

    @Override
    public MapObject[][] getCurrentMap()
    {
        return map;
    }

    @Override
    public ArrayList<GameObject> getGameObjects()
    {
        return gameObjects;
    }
}
