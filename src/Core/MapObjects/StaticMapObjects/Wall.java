package Core.MapObjects.StaticMapObjects;

import Core.Game.Game;
import Core.MapObjects.DynamicMapObjects.SnakeCell;
import Core.MapObjects.MapObject;
import View.Styles.GameDrawer;

/**
 * Created by Владимир on 16.09.2016.
 */
public class Wall extends StaticMapObject
{
    public Wall()
    {
    }

    @Override
    public void processCollision(MapObject mapObject, Game game)
    {
        mapObject.processCollision(this, game);
    }

    @Override
    public void processCollision(SnakeCell snakeCell, Game game)
    {
        snakeCell.processCollision(this, game);
    }

    @Override
    public void draw(GameDrawer drawer)
    {
        drawer.draw(this);
    }
}
