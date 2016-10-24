package Core.GameCommands;

import Core.Game.Game;
import Core.Utils.*;

/**
 * Created by ISmir on 19.09.2016.
 */
public class ChangeSnakeVelocityCommand implements GameCommand
{
    private final VelocityVector velocity;

    public ChangeSnakeVelocityCommand(VelocityVector velocity)
    {
        this.velocity = velocity;
    }

    @Override
    public void execute(Game game)
    {
        game.snake.setVelocity(velocity);
    }
}
