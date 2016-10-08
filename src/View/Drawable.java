package View;

import Core.Game.Game;
import View.Styles.GameStyle;

import javax.swing.*;

/**
 * Created by ISmir on 08.10.2016.
 */
public interface Drawable
{
    Icon getIcon(GameStyle style, Game game);
}
