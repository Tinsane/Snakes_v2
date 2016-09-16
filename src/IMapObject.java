/**
 * Created by Владимир on 16.09.2016.
 */
public interface IMapObject
{
    Vector getVelocity();
    void setVelocity(Vector velocity);
    boolean getIsDestructed();
    void setIsDestructed(boolean isDestructed);
    void processCollision(IMapObject collidedObject);
}