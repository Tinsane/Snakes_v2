package Core.MapObjects.StaticMapObjects.Berries;

import Core.Game.Game;
import View.Styles.GameStyle;

import javax.swing.*;

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
    public Icon getIcon(GameStyle style, Game game)
    {
        return style.getBlueberryIcon(this, game);
    }
}
