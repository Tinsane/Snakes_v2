package Core.GameObjects;

import Core.GameUpdatingSystem.GameUpdaters.GameMovementUpdater;
import Core.MapObjects.MapObject;

import java.io.Serializable;

/**
 * Created by ISmir on 26.11.2016.
 */
public interface GameObject<T extends MapObject> extends Serializable, Iterable<T>
{
    boolean contains(MapObject mapObject);
    boolean getIsDestructed();
    void updatePosition(GameMovementUpdater updater);
    void acceptVisitor(GameObjectVisitor visitor);
}
