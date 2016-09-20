package Tests;

import Game.Game;
import Game.GameCreator;
import GameCommands.ChangeSnakeVelocityCommand;
import Utils.VelocityVector;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Владимир on 20.09.2016.
 */
public class GameTest
{
    GameCreator gameCreator;

    @Before
    public void setUp()
    {
        gameCreator = new GameCreator();
        gameCreator.setMapSize(5, 5);
    }

    @Test
    public void testExpansion() throws Exception
    {
        gameCreator.placeBlueberry(2, 2);
        gameCreator.placeStrawberry(2, 3);
        Game game = gameCreator.createGame(2, 1, 1);
        game.executeCommand(new ChangeSnakeVelocityCommand(VelocityVector.right));
        game.update();
        assertEquals(game.snake.getLength(), 2);
        game.update();
        assertEquals(game.snake.getLength(), 5);
    }

    @Test
    public void testSelfIntersection() throws Exception
    {
        Game game = gameCreator.createGame(2, 1, 10);
        game.executeCommand(new ChangeSnakeVelocityCommand(VelocityVector.right));
        game.update();
        game.executeCommand(new ChangeSnakeVelocityCommand(VelocityVector.up));
        game.update();
        game.executeCommand(new ChangeSnakeVelocityCommand(VelocityVector.left));
        game.update();
        game.executeCommand(new ChangeSnakeVelocityCommand(VelocityVector.down));
        game.update();
        assertTrue(game.snake.getIsDestructed());
    }

    @Test
    public void testDestruction() throws Exception
    {
        gameCreator.placeWall(2, 2);
        Game game = gameCreator.createGame(2, 1, 1);
        game.executeCommand(new ChangeSnakeVelocityCommand(VelocityVector.right));
        game.update();
        assertTrue(game.snake.getIsDestructed());
    }

    @Test
    public void testCommandExecution() throws Exception
    {
        Game game = gameCreator.createGame(2, 1, 1);
        game.executeCommand(new ChangeSnakeVelocityCommand(VelocityVector.right));
        assertEquals(game.snake.head.getVelocity(), VelocityVector.right);
        assertEquals(game.getCurrentMap()[2][1].getVelocity(), VelocityVector.right);
        game.executeCommand(new ChangeSnakeVelocityCommand(VelocityVector.up));
        assertEquals(game.snake.head.getVelocity(), VelocityVector.up);
        assertEquals(game.getCurrentMap()[2][1].getVelocity(), VelocityVector.up);
    }
}