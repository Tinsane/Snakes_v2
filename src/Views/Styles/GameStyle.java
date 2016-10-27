package Views.Styles;

import Core.Game.GameAlike;
import Core.MapObjects.MapObjectVisitor;

import java.awt.*;

/**
 * Created by ISmir on 18.10.2016.
 */
public interface GameStyle
{
    Drawer CreateDrawer(Graphics2D g2d, GameAlike game, double turnPartLeft);

    int getTileSize();
}
