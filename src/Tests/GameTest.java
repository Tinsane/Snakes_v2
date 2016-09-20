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
    private static Game getStandardMapGame()
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
        return gameCreator.createGame(2, 1, 5);
    }

    @Test
    public void snakeCollisionTest()
    {
        Game game = getStandardMapGame();
        game.executeCommand(new ChangeSnakeVelocityCommand(VelocityVector.down));
        IntPair snakeHeadCoordinates = game.snake.head.getCoordinates(game.getCurrentMap());
        assertEquals(2, snakeHeadCoordinates.x);
        assertEquals(1, snakeHeadCoordinates.y);
        assertEquals(game.snake.head.getVelocity(), VelocityVector.down);
        assertEquals(game.getCurrentMap()[2][1].getVelocity(), VelocityVector.down);
        game.update();
        assertEquals(game.snake.getLength(), 6);
        game.update();
        assertTrue(game.snake.getIsDestructed());
        assertFalse(game.getCurrentMap()[2][3].getIsDestructed());
    }

    @Test
    public void snakeSelfCollisionTest()
    {
        Game game = getStandardMapGame();
        game.executeCommand(new ChangeSnakeVelocityCommand(VelocityVector.down));
        game.update();
        game.executeCommand(new ChangeSnakeVelocityCommand(VelocityVector.right));
        // not finished
    }
}