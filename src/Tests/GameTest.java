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
    public void update() throws Exception
    {
        GameCreator gameCreator = new GameCreator();
        gameCreator.setMapSize(5, 5);
        gameCreator.placeBlueberry(2, 2);
        gameCreator.placeWall(2, 3);
        Game game = gameCreator.createGame(2, 1, 1);
        game.executeCommand(new ChangeSnakeVelocityCommand(VelocityVector.right));
        IntPair snakeHeadCoordinates = game.snake.head.getCoordinates(game.getCurrentMap());
        assertEquals(2, snakeHeadCoordinates.x);
        assertEquals(1, snakeHeadCoordinates.y);
        assertEquals(game.snake.head.getVelocity(), VelocityVector.right);
        assertEquals(game.getCurrentMap()[2][1].getVelocity(), VelocityVector.right);
        game.update();
        assertEquals(game.snake.getLength(), 2);
        game.update();
        assertTrue(game.snake.getIsDestructed());
        assertFalse(game.getCurrentMap()[2][3].getIsDestructed());
    }
}