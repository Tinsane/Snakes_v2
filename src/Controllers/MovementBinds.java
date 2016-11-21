package Controllers;

import Core.Utils.VelocityVector;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**d
 * Created by ISmir on 21.10.2016.
 */
public class MovementBinds extends ArrayList<DirectionBind>
{
    public MovementBinds(int upKeyCode, int downKeyCode, int rightKeyCode, int leftKeyCode)
    {
        super();
        add(new DirectionBind(upKeyCode, VelocityVector.up));
        add(new DirectionBind(downKeyCode, VelocityVector.down));
        add(new DirectionBind(rightKeyCode, VelocityVector.right));
        add(new DirectionBind(leftKeyCode, VelocityVector.left));
    }

    public VelocityVector getDirection(KeyEvent e)
    {
        for(DirectionBind d : this)
            if (e.getKeyCode() == d.keyCode)
                return d.direction;
        return null;
    }
}
