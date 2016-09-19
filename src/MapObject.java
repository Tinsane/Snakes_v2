/**
 * Created by Владимир on 16.09.2016.
 */

// here interfaces don't start with I
public interface MapObject
{
    VelocityVector getVelocity();
    boolean getIsDestructed();
    void processCollision(MapObject collidedObject, Game game);
}