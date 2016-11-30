package Core.MapObjects.DynamicMapObjects;

import Core.Game.Game;
import Core.GameObjects.CatDog;
import Core.MapObjects.MapObject;
import Core.MapObjects.MapObjectVisitor;
import Core.MapObjects.StaticMapObjects.Berries.Berry;
import Core.MapObjects.StaticMapObjects.SandGlass;
import Core.MapObjects.StaticMapObjects.Wall;
import Core.Utils.VelocityVector;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Владимир on 27.11.2016.
 */
public class CatDogCell extends BigObjectCell
{
    public CatDogCell() { super(); }

    public CatDogCell(CatDogCell previous)
    {
        super(previous);
    }

    public CatDogCell(CatDogCell previous, VelocityVector velocity)
    {
        super(previous, velocity);
    }

    @Override
    public void processCollision(MapObject mapObject, Game game)
    {
        mapObject.processCollision(this, game);
    }

    @Override
    public void processCollision(Berry berry, Game game)
    {
        berry.setIsDestructed(true);
        CatDog.getCatDogOwner(game, this).extend(berry.getSatisfactionCoefficient());
    }

    @Override
    public void processCollision(SnakeCell snakeCell, Game game)
    {
        snakeCell.processCollision(this, game);
    }

    @Override
    public void processCollision(CatDogCell catDogCell, Game game)
    {
        CatDog snake = CatDog.getCatDogOwner(game, this);
        CatDog anotherSnake = CatDog.getCatDogOwner(game, catDogCell);
        if (this == snake.head)
            snake.setIsDestructed(true);
        if (catDogCell == anotherSnake.head)
            anotherSnake.setIsDestructed(true);
    }

    @Override
    public void processCollision(Wall wall, Game game)
    {
        throw new NotImplementedException();
    }

    @Override
    public void processCollision(SandGlass sandGlass, Game game)
    {
        throw new NotImplementedException();
    }

    @Override
    public void acceptVisitor(MapObjectVisitor visitor)
    {
        visitor.visit(this);
    }
}
