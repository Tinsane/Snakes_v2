package Core.MapObjects.DynamicMapObjects;

import Core.Game.Game;
import Core.MapObjects.MapObject;
import Core.MapObjects.StaticMapObjects.Berries.Berry;
import Core.MapObjects.StaticMapObjects.SandGlass;
import Core.MapObjects.StaticMapObjects.Wall;
import Core.GameObjects.Snake;
import Core.Utils.VelocityVector;
import Core.MapObjects.MapObjectVisitor;

/**
 * Created by Владимир on 16.09.2016.
 */
public class SnakeCell extends BigObjectCell
{
    public SnakeCell() {super();}

    public SnakeCell(SnakeCell previous)
    {
        super(previous);
    }

    public SnakeCell(SnakeCell previous, VelocityVector velocity)
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
        Snake.getSnakeOwner(game, this).extend(berry.getSatisfactionCoefficient());
    }

    @Override
    public void processCollision(SnakeCell snakeCell, Game game)
    {
        // let's just kill the snake
        Snake snake = Snake.getSnakeOwner(game, this);
        Snake anotherSnake = Snake.getSnakeOwner(game, snakeCell);
        if (this == snake.head)
            snake.setIsDestructed(true);
        if (snakeCell == anotherSnake.head)
            anotherSnake.setIsDestructed(true);
    }

    @Override
    public void processCollision(CatDogCell catDogCell, Game game)
    {
        Snake.getSnakeOwner(game, this).setIsDestructed(true);
    }

    @Override
    public void processCollision(Wall wall, Game game)
    {
        Snake.getSnakeOwner(game, this).setIsDestructed(true);
    }

    @Override
    public void processCollision(SandGlass sandGlass, Game game)
    {
//        game.rollback(sandGlass.getRollbackTurnsNumber());
        sandGlass.setIsDestructed(true);
    }

    @Override
    public void acceptVisitor(MapObjectVisitor visitor)
    {
        visitor.visit(this);
    }
}
