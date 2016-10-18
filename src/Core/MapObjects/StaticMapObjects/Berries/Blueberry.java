package Core.MapObjects.StaticMapObjects.Berries;

import Core.Game.Game;
import View.Styles.GameDrawer;

import java.awt.*;
import java.util.function.Consumer;

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
    public void draw(GameDrawer drawer)
    {
        drawer.draw(this);
    }
}
