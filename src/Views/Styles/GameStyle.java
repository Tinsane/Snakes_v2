package Views.Styles;

import Core.Game.Game;
import Core.Game.GameAlike;
import Core.MapObjects.MapObjectVisitor;

import java.awt.*;

/**
 * Created by ISmir on 18.10.2016.
 */
public interface GameStyle
{
    public MapObjectVisitor CreateDrawer(Graphics2D g2d, GameAlike game, double turnPartLeft);

    public int getTileSize();
}
