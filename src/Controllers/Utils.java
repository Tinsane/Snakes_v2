package Controllers;

import Core.Utils.VelocityVector;

import java.awt.event.KeyEvent;

/**
 * Created by ISmir on 21.10.2016.
 */
public class Utils
{
    public static VelocityVector getDirection(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_RIGHT:
                return VelocityVector.right;
            case KeyEvent.VK_LEFT:
                return VelocityVector.left;
            case KeyEvent.VK_DOWN:
                return VelocityVector.down;
            case KeyEvent.VK_UP:
                return VelocityVector.up;
            default:
                return null;
        }
    }
}
