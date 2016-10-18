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
import java.awt.image.BufferedImage;

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

    private void drawImage(BufferedImage image)
    {
        graphics.drawImage(image, x * style.getTileSize(), y * style.getTileSize(), null);
    }

    @Override
    public void draw(Wall wall)
    {
        drawImage(style.wallImage);
    }

    @Override
    public void draw(SandGlass sandGlass)
    {
        drawImage(style.sandGlassImage);
    }

    @Override
    public void draw(Strawberry strawberry)
    {
        drawImage(style.strawberryImage);
    }

    @Override
    public void draw(Blueberry blueberry)
    {
        drawImage(style.blueberryImage);
    }

    @Override
    public void draw(SnakeCell snakeCell)
    {
        drawImage(style.snakeCellImage);
    }

    @Override
    public void draw(EmptyCell emptyCell)
    {
        drawImage(style.emptyCellImage);
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
