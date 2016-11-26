package Core.GameObjects;

import Core.Game.GameUpdaters.GameUpdater;
import Core.MapObjects.MapObject;

import java.io.Serializable;

/**
 * Created by ISmir on 26.11.2016.
 */
public interface GameObject extends Serializable
{
    boolean contains(MapObject mapObject);
    boolean getIsDestructed();
    void updatePosition(GameUpdater updater);
}
