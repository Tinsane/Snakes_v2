package Views.Styles.Default;

import Core.Game.GameAlike;
import Core.GameObjects.Snake;
import Core.MapObjects.DynamicMapObjects.BigObjectCell;
import Core.MapObjects.DynamicMapObjects.SnakeCell;
import Core.Utils.IntPair;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Владимир on 21.11.2016.
 */
public class DefaultMultiplayerDrawer extends DefaultDrawer
{
    private final DefaultMultiplayerStyle style;

    public DefaultMultiplayerDrawer(DefaultMultiplayerStyle style, Graphics2D graphics, GameAlike game, double turnPartLeft)
    {
        super(style, graphics, game, turnPartLeft);
        this.style = style;
    }

    @Override
    protected void addSnakeCellImage(Snake owner, SnakeCell cell)
    {
        addRotatedImage(cell == owner.head ?
                style.snakesHeads.get(game.getIndex(owner)) :
                style.snakesCells.get(game.getIndex(owner)),
                cell.getVelocity(), 2);
    }
}
