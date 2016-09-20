package GameCommands;

import MapObjects.BaseMapObject;
import MapObjects.DynamicMapObjects.*;
import MapObjects.*;
import MapObjects.StaticMapObjects.*;
import MapObjects.StaticMapObjects.Berries.*;
import Game.Game;
import Utils.*;

/**
 * Created by ISmir on 19.09.2016.
 */
public class ChangeSnakeVelocityCommand implements GameCommand
{
    public VelocityVector velocity;

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
