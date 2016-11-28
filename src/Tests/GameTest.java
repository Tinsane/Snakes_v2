package Tests;

import Core.Game.Game;
import Core.Game.GameCreators.GameCreator;
import Core.Game.GameUpdaters.OneBerryGameUpdater;
import Core.GameCommands.ChangeSnakeVelocityCommand;
import Core.GameObjects.Snake;
import Core.Utils.IntPair;
import Core.Utils.VelocityVector;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Владимир on 20.09.2016.
 */
public class GameTest
{
    GameCreator gameCreator;

    @Before
    public void setUp()
    {
        gameCreator = new GameCreator(new OneBerryGameUpdater());
    }

    @Test
    public void testCommandsExecution()
    {
        gameCreator.setMapSize(1, 1);
        gameCreator.placeSnake(0, 0, 1);
        Game game = gameCreator.createGame();
        game.executeCommand(new ChangeSnakeVelocityCommand(0, VelocityVector.down));
        assertEquals(VelocityVector.down, Snake.getSnake(game).head.getVelocity());
        game.executeCommand(new ChangeSnakeVelocityCommand(0, VelocityVector.left));
        assertEquals(VelocityVector.left, Snake.getSnake(game).head.getVelocity());
    }

    @Test
    public void testSnakeMovement()
    {
        gameCreator.setMapSize(3, 3);
        gameCreator.placeSnake(1, 1, 10);
        Game game = gameCreator.createGame();
        game.executeCommand(new ChangeSnakeVelocityCommand(0, VelocityVector.left));
        game.update();
        IntPair snakeHeadPos = Snake.getSnake(game).head.getCoordinates(game.getCurrentMap());
        assertEquals(1, snakeHeadPos.x);
        assertEquals(2, snakeHeadPos.y);
        game.executeCommand(new ChangeSnakeVelocityCommand(0, VelocityVector.up));
        game.update();
        snakeHeadPos = Snake.getSnake(game).head.getCoordinates(game.getCurrentMap());
        assertEquals(1, snakeHeadPos.x);
        assertEquals(1, snakeHeadPos.y);
    }


    @Test
    public void testSnakeExtension()
    {
        gameCreator.setMapSize(3, 3);
        gameCreator.placeBlueberry(1, 2);
        gameCreator.placeStrawberry(2, 2);
        gameCreator.placeSnake(1, 1, 5);
        Game game = gameCreator.createGame();
        game.executeCommand(new ChangeSnakeVelocityCommand(0, VelocityVector.down));
        game.update();
        assertEquals(Snake.getSnake(game).getLength(), 6);
        game.executeCommand(new ChangeSnakeVelocityCommand(0, VelocityVector.right));
        game.update();
        assertEquals(Snake.getSnake(game).getLength(), 9);
    }

    @Test
    public void testSnakeDestruction()
    {
        gameCreator.setMapSize(3, 3);
        gameCreator.placeWall(1, 2);
        gameCreator.placeSnake(1, 1, 5);
        Game game = gameCreator.createGame();
        game.executeCommand(new ChangeSnakeVelocityCommand(0, VelocityVector.down));
        game.update();
        assertTrue(Snake.getSnake(game).getIsDestructed());
    }

    @Test
    public void testSelfIntersection()
    {
        gameCreator.setMapSize(3, 3);
        gameCreator.placeSnake(1, 1, 10);
        Game game = gameCreator.createGame();
        game.executeCommand(new ChangeSnakeVelocityCommand(0, VelocityVector.down));
        game.update();
        game.executeCommand(new ChangeSnakeVelocityCommand(0, VelocityVector.right));
        game.update();
        game.executeCommand(new ChangeSnakeVelocityCommand(0, VelocityVector.up));
        game.update();
        assertFalse(Snake.getSnake(game).getIsDestructed());
        game.executeCommand(new ChangeSnakeVelocityCommand(0, VelocityVector.left));
        game.update();
        assertTrue(Snake.getSnake(game).getIsDestructed());
    }
}