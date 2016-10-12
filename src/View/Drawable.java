package View;

import Core.Game.Game;
import View.Styles.GameStyle;

import java.awt.*;

/**
 * Created by ISmir on 08.10.2016.
 */
public interface Drawable
{
    Image getImage(GameStyle style, Game game);
}
