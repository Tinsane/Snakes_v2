package Core.MapObjects.DynamicMapObjects;

import Core.MapObjects.MapObject;
import Core.Utils.IntPair;
import Core.Utils.VelocityVector;

/**
 * Created by Владимир on 27.11.2016.
 */
public abstract class BigObjectCell extends DynamicMapObject
{
    public BigObjectCell previous;

    public BigObjectCell() {this(null);}

    public BigObjectCell(BigObjectCell previous)
    {
        this(previous, VelocityVector.zero);
    }

    public BigObjectCell(BigObjectCell previous, VelocityVector velocity)
    {
        this.previous = previous;
        this.setVelocity(velocity);
    }

    public IntPair getPreviousCoordinates(MapObject[][] map, IntPair coordinates)
    {
        VelocityVector direction = getDirectionToPrevious(map, coordinates);
        if (direction == null)
            return null;
        return coordinates.getAdded(direction.getIntPair());
    }

    public VelocityVector getDirectionToPrevious(MapObject[][] map, IntPair coordinates)
    {
        for (VelocityVector direction : VelocityVector.directions)
        {
            IntPair newPair = coordinates.getAdded(direction.getIntPair());
            if (map[newPair.x][newPair.y] == previous)
                return direction;
        }
        return null;
    }
}
