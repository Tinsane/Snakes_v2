package Core.MapObjects.StaticMapObjects.Berries;

import Core.Game.Game;
import View.Styles.GameStyle;

import java.awt.*;
import java.util.function.Consumer;

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
    public void draw(GameStyle style, Game game, Consumer<Image> drawer)
    {
        style.draw(this, game, drawer);
    }
}
