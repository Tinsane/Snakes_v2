package Core.MapObjects.StaticMapObjects.Berries;

import Core.Game.Game;
import View.Styles.GameStyle;

import java.awt.*;

/**
 * Created by ISmir on 18.09.2016.
 */
public class Strawberry extends Berry
{
    public Strawberry()
    {
        super(3);
    }

    @Override
    public Image getImage(GameStyle style, Game game)
    {
        return style.getStrawberryImage(this, game);
    }
}
