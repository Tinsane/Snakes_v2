/**
 * Created by ISmir on 17.09.2016.
 */
public abstract class DynamicMapObject extends BaseMapObject
{
    private VelocityVector velocity;

    @Override
    public VelocityVector getVelocity()
    {
        return velocity;
    }

    public void setVelocity(VelocityVector velocity)
    {
        this.velocity = velocity;
    }
}
