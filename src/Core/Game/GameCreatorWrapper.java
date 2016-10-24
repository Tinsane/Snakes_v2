package Core.Game;

import Core.MapObjects.MapObject;
import Core.MapObjects.StaticMapObjects.Berries.Blueberry;
import Core.MapObjects.StaticMapObjects.Berries.Strawberry;
import Core.MapObjects.StaticMapObjects.EmptyCell;
import Core.MapObjects.StaticMapObjects.Wall;
import Core.Snake.Snake;
import Core.Utils.IntPair;
import Core.Utils.VelocityVector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ISmir on 20.10.2016.
 */
public class GameCreatorWrapper extends GameCreator
{
    public enum Pointer
    {
        MapObjectType, MapPosition
    }

    private List<MapObject> mapObjects = new ArrayList<MapObject>(); // wall and berries

    public IntPair mapPosition = new IntPair(1, 1);
    private int mapObjectIndex = 0;

    public Pointer pointer = Pointer.MapPosition;

    {
        addObject(new EmptyCell());
        addObject(new Wall());
        addObject(new Strawberry());
        addObject(new Blueberry());
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
        mapPosition = newPosition;
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

    public void addObject(MapObject obj)
    {
        mapObjects.add(obj);
    }

    public void placeMapObject()
    {
        placeMapObject(mapObjects.get(mapObjectIndex));
    }

    public void placeMapObject(MapObject mapObject)
    {
        placeMapObject(mapPosition.x - 1, mapPosition.y - 1, mapObject);
    }

//    public void placeSnake(int length)
//    {
//        if (length < 1)
//            throw new IllegalArgumentException(String.format("Snake length should be positive. Given : %1$d", length));
//        Snake snake = new Snake(length);
//        placeMapObject(snake.head);
//    }
}
