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
        return getIndex(getOwner(mapObject));
    }

    @Override
    public int getIndex(GameObject gameObject)
    {
        ArrayList<GameObject> gameObjects = getGameObjects();
        for(int i = 0; i < gameObjects.size(); ++i)
            if (gameObjects.get(i) == gameObject)
                return i;
        return -1;
    }

    public GameObject getOwner(MapObject mapObject)
    {
        ArrayList<GameObject> gameObjects = getGameObjects();
        for(GameObject gameObject : gameObjects)
            if (gameObject.contains(mapObject))
                return gameObject;
        return null;
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
