package Core.Game;

import Core.MapObjects.DynamicMapObjects.SnakeCell;
import Core.MapObjects.MapObject;
import Core.MapObjects.StaticMapObjects.Berries.Blueberry;
import Core.MapObjects.StaticMapObjects.Berries.Strawberry;
import Core.MapObjects.StaticMapObjects.EmptyCell;
import Core.MapObjects.StaticMapObjects.Wall;
import Core.Utils.IntPair;
import Core.Utils.VelocityVector;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by ISmir on 20.10.2016.
 */
public class GameCreatorWrapper extends GameCreator
{
    public enum Pointer
    {
        MapObjectType, MapPosition
    }

    private List<Supplier<MapObject>> mapObjects = new ArrayList<>();

    public void Populate(GameAlike game)
    {
        map = game.getCurrentMap();
        snakes = game.getSnakes();
    }

    public List<MapObject> getMapObjects()
    {
        return mapObjects
                .stream()
                .map(Supplier::get)
                .collect(Collectors.toList());
    }

    public int getMapObjectsCount()
    {
        return mapObjects.size();
    }

    public void updateMapPosition(IntPair mapPosition)
    {
        this.mapPosition = mapPosition;
        clearBorders();
    }

    public IntPair getMapPosition()
    {
        return mapPosition;
    }

    private IntPair mapPosition = new IntPair(1, 1);
    public int mapObjectIndex = 0;

    public Pointer pointer = Pointer.MapPosition;

    {
        addObject(EmptyCell::new);
        addObject(Wall::new);
        addObject(Strawberry::new);
        addObject(Blueberry::new);
    }

    public GameCreatorWrapper()
    {
        super();
    }

    public GameCreatorWrapper(int width, int height)
    {
        super();
        resizeMap(width, height);
    }

    public void movePositionOrStay(VelocityVector vector)
    {
        setPositionOrStay(mapPosition.getAdded(vector.getIntPair()));
    }

    public void setPositionOrStay(IntPair newPosition)
    {
        if (!(isCellInMap(newPosition)))
            return;
        updateMapPosition(newPosition);
    }

    public void movePositionWithResizing(VelocityVector vector)
    {
        setPositionWithResizing(mapPosition.getAdded(vector.getIntPair()));
    }

    public void setPositionWithResizing(IntPair newPosition)
    {
        if (isCellInMap(newPosition))
            updateMapPosition(newPosition);
        else
        {
            IntPair xSizeAndCoordinate = getNewSizeAndCoordinate(getWidth(), newPosition.x);
            IntPair ySizeAndCoordinate = getNewSizeAndCoordinate(getHeight(), newPosition.y);
            resizeMap(xSizeAndCoordinate.x - 2, ySizeAndCoordinate.x - 2, Wall::new);
            updateMapPosition(new IntPair(xSizeAndCoordinate.y, ySizeAndCoordinate.y));
        }
    }

    public void moveMapObjectIndexOrStay(int shift)
    {
        setMapObjectIndexOrStay(mapObjectIndex + shift);
    }

    public void setMapObjectIndexOrStay(int newIndex)
    {
        if (newIndex < 0 || newIndex >= mapObjects.size())
            return;
        mapObjectIndex = newIndex;
    }

    public void addObject(Supplier<MapObject> supplier)
    {
        mapObjects.add(supplier);
    }

    public void placeMapObject()
    {
        placeMapObject(mapObjects.get(mapObjectIndex).get());
    }

    public void placeMapObject(MapObject mapObject)
    {
        placeMapObject(mapPosition.x - 1, mapPosition.y - 1, mapObject);
    }

    //First element is newSize
    //Second is newCoordinate
    private IntPair getNewSizeAndCoordinate(int currentSize, int coordinateToFit)
    {
        if (coordinateToFit <= 0)
            return new IntPair(currentSize, 1);
            //return new IntPair(currentSize + (1 - coordinateToFit),  1);
        if (coordinateToFit >= currentSize - 1)
            return new IntPair(currentSize + (coordinateToFit - currentSize + 2), coordinateToFit);
        return new IntPair(currentSize, coordinateToFit);
    }

    public void clearBorders()
    {
        int cleanLines = 0;
        int cleanColumns = 0;

        for (int y = getHeight() - 2; y >= 0; --y)
            if (!lineOfWalls(y) || mapPosition.y == y)
            {
                cleanLines = getHeight() - 2 - y;
                break;
            }
        for (int x = getWidth() - 2; x >= 0; --x)
            if (!columnOfWalls(x) || mapPosition.x == x)
            {
                cleanColumns = getWidth() - 2 - x;
                break;
            }

        if (cleanColumns == 0 && cleanLines == 0)
            return;
        resizeMap(getWidth() - 2 - cleanColumns, getHeight() - 2 - cleanLines);
    }

    public void placeSnake()
    {
        placeSnake(mapPosition.x - 1, mapPosition.y - 1, 1);
    }

    private boolean lineOfWalls(int lineNumber)
    {
        for (int i = 0; i < getWidth(); ++i)
            if (!(map[i][lineNumber] instanceof Wall))
                return false;
        return true;
    }

    private boolean columnOfWalls(int columnNumber)
    {
        for (int i  = 0; i < getHeight(); ++i)
            if (!(map[columnNumber][i] instanceof Wall))
                return false;
        return true;
    }

    private IntPair findSnakePosition()
    {
        for (int i = 0; i < getWidth(); ++i)
            for (int j = 0; j < getHeight(); ++j)
                if (map[i][j] instanceof SnakeCell)
                    return new IntPair(i - 1, j - 1);
        return null;
    }
}
