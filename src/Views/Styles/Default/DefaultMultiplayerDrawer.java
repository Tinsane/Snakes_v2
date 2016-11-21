package Views.Styles.Default;

import Core.Game.GameAlike;
import Core.MapObjects.DynamicMapObjects.SnakeCell;

import java.awt.*;

/**
 * Created by Владимир on 21.11.2016.
 */
public class DefaultMultiplayerDrawer extends DefaultDrawer
{
    public DefaultMultiplayerDrawer(DefaultMultiplayerStyle style, Graphics2D graphics, GameAlike game, double turnPartLeft)
    {
        super(style, graphics, game, turnPartLeft);
    }

    @Override
    public void visit(SnakeCell snakeCell)
    {
        addSnakeCell(snakeCell, ((DefaultMultiplayerStyle)style).snakesHeads.get(game.getOwnerIndex(snakeCell)),
                ((DefaultMultiplayerStyle)style).snakesCells.get(game.getOwnerIndex(snakeCell)));
    }
}
