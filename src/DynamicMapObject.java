/**
 * Created by ISmir on 17.09.2016.
 */
public abstract class DynamicMapObject extends BaseMapObject
{
    public Vector velocity;

    @Override
    public Vector getVelocity()
    {
        return velocity;
    }

    public boolean setVelocity(Vector velocity)
    {
        this.velocity = velocity;
    }
}
