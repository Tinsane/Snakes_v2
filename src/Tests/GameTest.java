package Tests;

import Game.Game;
import Game.GameCreator;
import GameCommands.ChangeSnakeVelocityCommand;
import Utils.IntPair;
import Utils.VelocityVector;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Владимир on 20.09.2016.
 */
public class GameTest 
{
    Game game;

    @Before
    public void setUp()
    {
        // that's how the map looks like
        // snake length is 5
        // S - snake
        // B - blueberry
        // W - wall
        // C - Ctrawberry
        //.....
        //..S..
        //..BC.
        //..W..
        //.....
        GameCreator gameCreator = new GameCreator();
        gameCreator.setMapSize(5, 5);
        gameCreator.placeBlueberry(2, 2);
        gameCreator.placeStrawberry(3, 2);
        gameCreator.placeWall(2, 3);
        game = gameCreator.createGame(2, 1, 5);
    }

    @Test
    public void testCommandsExecution()
    {
        game.executeCommand(new ChangeSnakeVelocityCommand(VelocityVector.down));
        assertEquals(VelocityVector.down, game.snake.head.getVelocity());
        game.executeCommand(new ChangeSnakeVelocityCommand(VelocityVector.left));
        assertEquals(VelocityVector.left, game.snake.head.getVelocity());
    }

    @Test
    public void testSnakeMovement()
    {
        game.executeCommand(new ChangeSnakeVelocityCommand(VelocityVector.left));
        game.update();
        IntPair snakeHeadPos = game.snake.head.getCoordinates(game.getCurrentMap());
        assertEquals(1, snakeHeadPos.x);
        assertEquals(1, snakeHeadPos.y);
        game.executeCommand(new ChangeSnakeVelocityCommand(VelocityVector.up));
        game.update();
        snakeHeadPos = game.snake.head.getCoordinates(game.getCurrentMap());
        assertEquals(1, snakeHeadPos.x);
        assertEquals(0, snakeHeadPos.y);
    }


    @Test
    public void testSnakeExtension()
    {
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
        game.executeCommand(new ChangeSnakeVelocityCommand(VelocityVector.down));
        game.update();
        game.update();
        assertTrue(game.snake.getIsDestructed());
    }

    @Test
    public void testSelfIntersection()
    {
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