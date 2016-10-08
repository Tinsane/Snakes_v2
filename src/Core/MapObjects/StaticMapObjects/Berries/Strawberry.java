package Core.MapObjects.StaticMapObjects.Berries;

import Core.Game.Game;
import View.Styles.GameStyle;

import javax.swing.*;

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
    public Icon getIcon(GameStyle style, Game game)
    {
        return style.getStrawberryIcon(this, game);
    }
}
