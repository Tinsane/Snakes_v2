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
import View.Styles.GameStyle;
import com.sun.corba.se.impl.orbutil.graph.Graph;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.util.function.Consumer;

/**
 * Created by ISmir on 08.10.2016.
 */
public class DefaultDrawer implements GameDrawer
{
    private int x, y;
    private Game game;
    private double turnPartLeft;
    private Graphics2D graphics;
    private GameStyle style;

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
        throw new NotImplementedException();
    }

    @Override
    public void draw(SandGlass sandGlass)
    {
        throw new NotImplementedException();
    }

    @Override
    public void draw(Strawberry strawberry)
    {
        throw new NotImplementedException();
    }

    @Override
    public void draw(Blueberry blueberry)
    {
        throw new NotImplementedException();
    }

    @Override
    public void draw(SnakeCell snakeCell)
    {
        throw new NotImplementedException();
    }

    @Override
    public void draw(EmptyCell emptyCell)
    {
        throw new NotImplementedException();
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
            for(int j = 0; j < map[0].length; ++j)
            {
                int finalI = i;
                int finalJ = j;
                draw(map[i][j], i, j);
//                map[i][j].draw(style, game, (Image image) ->
//                        graphics.drawImage(image, finalI * style.getTileSize(), finalJ * style.getTileSize(), null));
            }
    }
}
