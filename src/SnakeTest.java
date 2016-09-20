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
    public void setVelocity() throws Exception
    {
        for (VelocityVector velocity : new VelocityVector[]{VelocityVector.down, VelocityVector.left,
                VelocityVector.zero})
        {
            snake.setVelocity(velocity);
            assertEquals(velocity, snake.head.getVelocity());
        }
    }

    private int getSnakeLength()
    {
        int length = 0;
        for (SnakeCell current = snake.head; current != null; current = current.previous)
            ++length;
        return length;
    }

    @org.junit.Test
    public void incLength() throws Exception
    {
        for (int expectedLength = 1; expectedLength <= 10; ++expectedLength)
        {
            assertEquals(expectedLength, getSnakeLength());
            snake.incLength();
        }
    }

    @org.junit.Test
    public void extend() throws Exception
    {
        int expectedLength = 1;
        for (int extension : new int[]{1, 2, 3, 4, 5, 30})
        {
            expectedLength += extension;
            snake.extend(extension);
            assertEquals(expectedLength, getSnakeLength());
        }
    }

    @org.junit.Test
    public void setIsDestructed() throws Exception
    {
        snake.extend(10);
        snake.setIsDestructed(true);
        assertTrue(snake.getIsDestructed());
        for (SnakeCell curCell = snake.head; curCell != null; curCell = curCell.previous)
            assertTrue(curCell.getIsDestructed());
    }

}