package View;

import Core.Game.Game;
import View.Styles.GameStyle;

import java.awt.*;
import java.util.function.Consumer;

/**
 * Created by ISmir on 08.10.2016.
 */
public interface Drawable
{
    void draw(GameStyle style, Game game, Consumer<Image> drawer);
}
