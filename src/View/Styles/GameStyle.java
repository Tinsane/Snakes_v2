package View.Styles;

import Core.Game.Game;

import java.awt.*;

/**
 * Created by ISmir on 18.10.2016.
 */
public interface GameStyle
{
    GameDrawer CreateDrawer(Graphics2D g2d, Game game, double turnPartLeft);

    public int getTileSize();
}
