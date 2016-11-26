package Core.Game;

import Core.GameObjects.GameObject;
import Core.MapObjects.MapObject;

import java.util.ArrayList;

/**
 * Created by ISmir on 23.10.2016.
 */
public interface GameAlike
{
    MapObject[][] getCurrentMap();

    ArrayList<GameObject> getGameObjects();
    GameObject getGameObject(Class objectClass, int index);
    GameObject getOwner(MapObject cell);
    int getOwnerIndex(MapObject cell);

    int getWidth();
    int getHeight();
}
