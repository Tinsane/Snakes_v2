package Tests;

import Core.MapObjects.DynamicMapObjects.SnakeCell;
import Core.GameObjects.Snake.Snake;
import Core.Utils.VelocityVector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Владимир on 20.09.2016.
 */
public class SnakeTest
{
    Snake snake;

    @org.junit.Before
    public void setUp() throws Exception
    {
        snake = new Snake(1);
    }

    @org.junit.Test
    public void setVelocity()
    {
        for (VelocityVector velocity : new VelocityVector[]{VelocityVector.down, VelocityVector.left,
                VelocityVector.zero})
        {
            snake.setVelocity(velocity);
            assertEquals(velocity, snake.head.getVelocity());
        }
    }

    @org.junit.Test
    public void incLength()
    {
        for (int expectedLength = 1; expectedLength <= 10; ++expectedLength)
        {
            assertEquals(expectedLength, snake.getLength());
            snake.incLength();
        }
    }

    @org.junit.Test
    public void extend()
    {
        int expectedLength = 1;
        for (int extension : new int[]{1, 2, 3, 4, 5, 30})
        {
            expectedLength += extension;
            snake.extend(extension);
            assertEquals(expectedLength, snake.getLength());
        }
    }

    @org.junit.Test
    public void setIsDestructed() throws Exception
    {
        snake.extend(10);
        snake.setIsDestructed(true);
        assertTrue(snake.getIsDestructed());
        for (SnakeCell cell : snake)
            assertTrue(cell.getIsDestructed());
    }

}