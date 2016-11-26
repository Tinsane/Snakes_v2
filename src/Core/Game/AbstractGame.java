package Core.Game;

import Core.GameObjects.GameObject;
import Core.MapObjects.MapObject;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Владимир on 21.11.2016.
 */
public abstract class AbstractGame implements GameAlike
{

    public int getWidth() { return getCurrentMap().length; }

    public int getHeight() { return getCurrentMap()[0].length; }

    public int getOwnerIndex(MapObject mapObject)
    {
        ArrayList<GameObject> gameObjects = getGameObjects();
        for(int i = 0; i < gameObjects.size(); ++i)
            if (gameObjects.get(i).contains(mapObject))
                return i;
        return -1;
    }

    public GameObject getOwner(MapObject mapObject)
    {
        int ownerIndex = getOwnerIndex(mapObject);
        return (ownerIndex != -1) ? getGameObjects().get(ownerIndex) : null;
    }

    @Override
    public GameObject getGameObject(Class gameObjectClass, int index)
    {
        Optional<GameObject> found = getGameObjects()
                .stream()
                .filter(gameObjectClass::isInstance)
                .skip(index).findFirst();
        if (found.isPresent())
            return found.get();
        return null;
    }
}
