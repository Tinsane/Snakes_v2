import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Владимир on 16.09.2016.
 */
public class SnakeCell extends DynamicMapObject
{
    public void processCollision(SnakeCell snakeCell, Game game)
    {
        // let's just kill the snake
        game.snake.setIsDestructed(true);
    }

    public void processCollision(Berry berry, Game game)
    {
        // probably need to check if cell is head
        berry.setIsDestructed(true);
        game.snake.extend(berry.getSatisfactionCoefficient());
    }

    public void processCollision(SandGlass sandGlass, Game game)
    {
        game.rollback(sandGlass.rollbackTurnsNumber);
        sandGlass.setIsDestructed(true);
    }

    public void processCollision(Wall wall, Game game)
    {
        game.snake.setIsDestructed(true);
    }
}
