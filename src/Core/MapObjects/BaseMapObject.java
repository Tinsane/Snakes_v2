package Core.MapObjects;

import java.io.Serializable;

/**
 * Created by ISmir on 17.09.2016.
 */
public abstract class BaseMapObject implements MapObject, Serializable
{
    public boolean isDestructed = false;

    @Override
    public boolean getIsDestructed()
    {
        return isDestructed;
    }

    public void setIsDestructed(boolean isDestructed)
    {
        this.isDestructed = isDestructed;
    }
}
