package Core.GameUpdatingSystem.GameUpdaters;

import Core.Game.Game;
import Core.GameObjects.GameObject;
import Core.MapObjects.MapObject;
import Core.MapObjects.StaticMapObjects.EmptyCell;
import Core.Utils.IntPair;

/**
 * Created by Владимир on 28.11.2016.
 */
public class GameMovementUpdater extends BaseGameUpdater
{
    private Game game;
    private MapObject[][] newMap;
    public GameMovementUpdater()
    {
        super(1);
    }

    @Override
    public void execute(Game game)
    {
        this.game = game;
        newMap = new MapObject[game.getWidth()][game.getHeight()];
        updateGame();
        game.setCurrentMap(newMap);
    }

    public MapObject[][] getCurrentMap()
    {
        return game.getCurrentMap();
    }

    public void placeObject(MapObject object, IntPair position)
    {
        MapObject currentlyPlacedObject = newMap[position.x][position.y];
        if (currentlyPlacedObject != null)
        {
            object.processCollision(currentlyPlacedObject, game);
            if (!currentlyPlacedObject.getIsDestructed() && !object.getIsDestructed())
                throw new IllegalArgumentException(
                        String.format("Objects %s and %s can't decide which one stays alive.",
                                object.getClass(), currentlyPlacedObject.getClass()));
        }
        if (!object.getIsDestructed())
            newMap[position.x][position.y] = object;
    }

    public void moveObject(IntPair position)
    {
        MapObject[][] curMap = game.getCurrentMap();
        MapObject curObject = curMap[position.x][position.y];
        IntPair newPosition = position.getAdded(curObject.getVelocity().getIntPair());
        placeObject(curObject, newPosition);
    }

    private void updateGame()
    {
        newMap = new MapObject[game.getWidth()][game.getHeight()];
        MapObject[][] curMap = getCurrentMap();
        for (int x = 0; x < curMap.length; ++x)
            for (int y = 0; y < curMap[0].length; ++y)
            {
                MapObject curObject = curMap[x][y];
                if (!(curObject.getIsDestructed() || curObject.getClass() == EmptyCell.class || game.getOwner(curObject) != null))
                    moveObject(new IntPair(x, y));
            }
        for (GameObject gameObject : game.gameObjects)
            gameObject.updatePosition(this);
        clearDestructedObjects();
        fillEmptyCells();
    }

    private void clearDestructedObjects()
    {
        for (int x = 0; x < newMap.length; ++x)
            for (int y = 0; y < newMap[0].length; ++y)
                if (newMap[x][y] != null && newMap[x][y].getIsDestructed())
                    newMap[x][y] = null;
    }

    private void fillEmptyCells()
    {
        for (int x = 0; x < newMap.length; ++x)
            for (int y = 0; y < newMap[0].length; ++y)
                if (newMap[x][y] == null)
                    newMap[x][y] = new EmptyCell();
    }
}
