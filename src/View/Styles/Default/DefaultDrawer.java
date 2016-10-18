package View.Styles.Default;

import Core.Game.Game;
import Core.MapObjects.DynamicMapObjects.SnakeCell;
import Core.MapObjects.MapObject;
import Core.MapObjects.StaticMapObjects.Berries.Blueberry;
import Core.MapObjects.StaticMapObjects.Berries.Strawberry;
import Core.MapObjects.StaticMapObjects.EmptyCell;
import Core.MapObjects.StaticMapObjects.SandGlass;
import Core.MapObjects.StaticMapObjects.Wall;
import View.Styles.GameDrawer;

import java.awt.*;

/**
 * Created by ISmir on 08.10.2016.
 */
public class DefaultDrawer implements GameDrawer
{
    private int x, y;
    private Game game;
    private double turnPartLeft;
    private Graphics2D graphics;
    private DefaultStyle style;

    public DefaultDrawer(DefaultStyle style, Graphics2D graphics, Game game, double turnPartLeft)
    {
        this.game = game;
        this.turnPartLeft = turnPartLeft;
        this.graphics = graphics;
        this.style = style;
    }

    @Override
    public void draw(Wall wall)
    {
        graphics.drawImage(style.wallImage, x * style.getTileSize(), y * style.getTileSize(), null);
    }

    @Override
    public void draw(SandGlass sandGlass)
    {
        graphics.drawImage(style.sandGlassImage, x * style.getTileSize(), y * style.getTileSize(), null);
    }

    @Override
    public void draw(Strawberry strawberry)
    {
        graphics.drawImage(style.strawberryImage, x * style.getTileSize(), y * style.getTileSize(), null);
    }

    @Override
    public void draw(Blueberry blueberry)
    {
        graphics.drawImage(style.blueberryImage, x * style.getTileSize(), y * style.getTileSize(), null);
    }

    @Override
    public void draw(SnakeCell snakeCell)
    {
        graphics.drawImage(style.snakeCellImage, x * style.getTileSize(), y * style.getTileSize(), null);
    }

    @Override
    public void draw(EmptyCell emptyCell)
    {
        graphics.drawImage(style.emptyCellImage, x * style.getTileSize(), y * style.getTileSize(), null);
    }

    public void draw(MapObject mapObject, int x, int y)
    {
        this.x = x;
        this.y = y;
        mapObject.draw(this);
    }

    public void draw()
    {
        MapObject[][] map = game.getCurrentMap();
        for (int i = 0; i < map.length; ++i)
            for (int j = 0; j < map[0].length; ++j)
                draw(map[i][j], i, j);
    }
}
