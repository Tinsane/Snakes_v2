package View.Styles;

import Core.Game.Game;

import java.awt.*;

/**
 * Created by ISmir on 18.10.2016.
 */
public interface GameStyle
{
    public GameDrawer CreateDrawer(Graphics2D g2d, Game game, double turnPartLeft);

    public int getTileSize();
}
