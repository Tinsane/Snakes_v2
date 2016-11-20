package Tests;

import Core.Game.Game;
import Core.Game.GameCreator;
import Core.GameCommands.ChangeSnakeVelocityCommand;
import Core.Utils.IntPair;
import Core.Utils.VelocityVector;
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
        gameCreator.placeSnake(0, 0, 1);
        Game game = gameCreator.createGame();
        game.executeCommand(new ChangeSnakeVelocityCommand(0, VelocityVector.down));
        assertEquals(VelocityVector.down, game.snakes.get(0).head.getVelocity());
        game.executeCommand(new ChangeSnakeVelocityCommand(0, VelocityVector.left));
        assertEquals(VelocityVector.left, game.snakes.get(0).head.getVelocity());
    }

    @Test
    public void testSnakeMovement()
    {
        GameCreator gameCreator = new GameCreator();
        gameCreator.setMapSize(3, 3);
        gameCreator.placeSnake(1, 1, 10);
        Game game = gameCreator.createGame();
        game.executeCommand(new ChangeSnakeVelocityCommand(0, VelocityVector.left));
        game.update();
        IntPair snakeHeadPos = game.snakes.get(0).head.getCoordinates(game.getCurrentMap());
        assertEquals(1, snakeHeadPos.x);
        assertEquals(2, snakeHeadPos.y);
        game.executeCommand(new ChangeSnakeVelocityCommand(0, VelocityVector.up));
        game.update();
        snakeHeadPos = game.snakes.get(0).head.getCoordinates(game.getCurrentMap());
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
        gameCreator.placeSnake(1, 1, 5);
        Game game = gameCreator.createGame();
        game.executeCommand(new ChangeSnakeVelocityCommand(0, VelocityVector.down));
        game.update();
        assertEquals(game.snakes.get(0).getLength(), 6);
        game.executeCommand(new ChangeSnakeVelocityCommand(0, VelocityVector.right));
        game.update();
        assertEquals(game.snakes.get(0).getLength(), 9);
    }

    @Test
    public void testSnakeDestruction()
    {
        GameCreator gameCreator = new GameCreator();
        gameCreator.setMapSize(3, 3);
        gameCreator.placeWall(1, 2);
        gameCreator.placeSnake(1, 1, 5);
        Game game = gameCreator.createGame();
        game.executeCommand(new ChangeSnakeVelocityCommand(0, VelocityVector.down));
        game.update();
        assertTrue(game.snakes.get(0).getIsDestructed());
    }

    @Test
    public void testSelfIntersection()
    {
        GameCreator gameCreator = new GameCreator();
        gameCreator.setMapSize(3, 3);
        gameCreator.placeSnake(1, 1, 10);
        Game game = gameCreator.createGame();
        game.executeCommand(new ChangeSnakeVelocityCommand(0, VelocityVector.down));
        game.update();
        game.executeCommand(new ChangeSnakeVelocityCommand(0, VelocityVector.right));
        game.update();
        game.executeCommand(new ChangeSnakeVelocityCommand(0, VelocityVector.up));
        game.update();
        assertFalse(game.snakes.get(0).getIsDestructed());
        game.executeCommand(new ChangeSnakeVelocityCommand(0, VelocityVector.left));
        game.update();
        assertTrue(game.snakes.get(0).getIsDestructed());
    }
}