/**
 * Created by ISmir on 19.09.2016.
 */
public class ChangeSnakeVelocityCommand implements GameCommand
{
    public Vector velocity;

    public ChangeSnakeVelocityCommand(Vector velocity)
    {
        this.velocity = velocity;
    }

    @Override
    public void Execute(Game game)
    {
        game.snake.setVelocity(velocity);
    }
}
