package Core.Game.GameUpdaters;

import Core.Game.Game;
import Core.MapObjects.MapObject;
import Core.Utils.IntPair;

import java.io.Serializable;

/**
 * Created by ISmir on 26.11.2016.
 */
public interface GameUpdater extends Serializable
{
    MapObject[][] getUpdatedMap();
    MapObject[][] getCurrentMap();
    void placeObject(MapObject object, IntPair position);
    void moveObject(IntPair position);
    void setGame(Game game);
}
