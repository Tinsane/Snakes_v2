package Core.GameCommands;

import Core.Game.Game;
import Core.GameObjects.Snake;
import Core.Utils.*;

/**
 * Created by ISmir on 19.09.2016.
 */
public class ChangeSnakeVelocityCommand implements GameCommand
{
    private final int snakeIndex;
    private final VelocityVector velocity;

    public ChangeSnakeVelocityCommand(int snakeIndex, VelocityVector velocity)
    {
        this.snakeIndex = snakeIndex;
        this.velocity = velocity;
    }

    @Override
    public void execute(Game game)
    {
        Snake.getSnake(game, snakeIndex).setVelocity(velocity);
    }
}
