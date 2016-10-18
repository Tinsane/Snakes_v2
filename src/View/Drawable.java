package View;

import Core.Game.Game;
import View.Styles.GameDrawer;

import java.awt.*;
import java.util.function.Consumer;

/**
 * Created by ISmir on 08.10.2016.
 */
public interface Drawable
{
    void draw(GameDrawer drawer);
}
