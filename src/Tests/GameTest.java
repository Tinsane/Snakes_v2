package Tests;

import Game.Game;
import Game.GameCreator;
import GameCommands.ChangeSnakeVelocityCommand;
import Utils.IntPair;
import Utils.VelocityVector;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Владимир on 20.09.2016.
 */
public class GameTest
{
    @Test
    public void testCommandsExecution()
    {
        GameCreator gameCreator = new GameCreator();
        gameCreator.setMapSize(1, 1);
        Game game = gameCreator.createGame(0, 0, 1);
        game.executeCommand(new ChangeSnakeVelocityCommand(VelocityVector.down));
        assertEquals(VelocityVector.down, game.snake.head.getVelocity());
        game.executeCommand(new ChangeSnakeVelocityCommand(VelocityVector.left));
        assertEquals(VelocityVector.left, game.snake.head.getVelocity());
    }

    @Test
    public void testSnakeMovement()
    {
        GameCreator gameCreator = new GameCreator();
        gameCreator.setMapSize(3, 3);
        Game game = gameCreator.createGame(1, 1, 10);
        game.executeCommand(new ChangeSnakeVelocityCommand(VelocityVector.left));
        game.update();
        IntPair snakeHeadPos = game.snake.head.getCoordinates(game.getCurrentMap());
        assertEquals(1, snakeHeadPos.x);
        assertEquals(2, snakeHeadPos.y);
        game.executeCommand(new ChangeSnakeVelocityCommand(VelocityVector.up));
        game.update();
        snakeHeadPos = game.snake.head.getCoordinates(game.getCurrentMap());
        assertEquals(1, snakeHeadPos.x);
        assertEquals(1, snakeHeadPos.y);
    }


    @Test
    public void testSnakeExtension()
    {
        GameCreator gameCreator = new GameCreator();
        gameCreator.setMapSize(3, 3);
        gameCreator.placeBlueberry(1, 2);
        gameCreator.placeStrawberry(2, 2);
        Game game = gameCreator.createGame(1, 1, 5);
        game.executeCommand(new ChangeSnakeVelocityCommand(VelocityVector.down));
        game.update();
        assertEquals(game.snake.getLength(), 6);
        game.executeCommand(new ChangeSnakeVelocityCommand(VelocityVector.right));
        game.update();
        assertEquals(game.snake.getLength(), 9);
    }

    @Test
    public void testSnakeDestruction()
    {
        GameCreator gameCreator = new GameCreator();
        gameCreator.setMapSize(3, 3);
        gameCreator.placeWall(1, 2);
        Game game = gameCreator.createGame(1, 1, 5);
        game.executeCommand(new ChangeSnakeVelocityCommand(VelocityVector.down));
        game.update();
        assertTrue(game.snake.getIsDestructed());
    }

    @Test
    public void testSelfIntersection()
    {
        GameCreator gameCreator = new GameCreator();
        gameCreator.setMapSize(3, 3);
        Game game = gameCreator.createGame(1, 1, 10);
        game.executeCommand(new ChangeSnakeVelocityCommand(VelocityVector.down));
        game.update();
        game.executeCommand(new ChangeSnakeVelocityCommand(VelocityVector.right));
        game.update();
        game.executeCommand(new ChangeSnakeVelocityCommand(VelocityVector.up));
        game.update();
        assertFalse(game.snake.getIsDestructed());
        game.executeCommand(new ChangeSnakeVelocityCommand(VelocityVector.left));
        game.update();
        assertTrue(game.snake.getIsDestructed());
    }
}