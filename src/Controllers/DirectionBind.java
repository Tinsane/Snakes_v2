package Controllers;

import Core.Utils.VelocityVector;

/**
 * Created by Владимир on 20.11.2016.
 */
public class DirectionBind
{
    public final int keyCode;
    public final VelocityVector direction;

    public DirectionBind(int keyCode, VelocityVector direction)
    {
        this.keyCode = keyCode;
        this.direction = direction;
    }
}
