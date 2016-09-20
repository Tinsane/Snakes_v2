package MapObjects.StaticMapObjects;

import MapObjects.BaseMapObject;
import MapObjects.DynamicMapObjects.*;
import MapObjects.*;
import MapObjects.StaticMapObjects.*;
import MapObjects.StaticMapObjects.Berries.*;
import Game.Game;
import Utils.*;

/**
 * Created by ISmir on 17.09.2016.
 */
public abstract class StaticMapObject extends BaseMapObject
{
    @Override
    public VelocityVector getVelocity()
    {
        return VelocityVector.zero;
    }
}
