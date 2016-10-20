package Core.MapObjects.StaticMapObjects.Berries;

import Core.MapObjects.MapObjectVisitor;

/**
 * Created by ISmir on 18.09.2016.
 */
public class Blueberry extends Berry
{
    public Blueberry()
    {
        super(1);
    }

    @Override
    public void acceptVisitor(MapObjectVisitor visitor)
    {
        visitor.visit(this);
    }
}
