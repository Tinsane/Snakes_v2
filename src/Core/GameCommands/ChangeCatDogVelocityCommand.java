package Core.GameCommands;

import Core.Game.Game;
import Core.GameObjects.CatDog;
import Core.Utils.VelocityVector;

/**
 * Created by Владимир on 28.11.2016.
 */
public class ChangeCatDogVelocityCommand implements GameCommand
{
    private final CatDog catDog;
    private final VelocityVector velocity;
    public ChangeCatDogVelocityCommand(CatDog catDog, VelocityVector velocity)
    {
        this.catDog = catDog;
        this.velocity = velocity;
    }
    @Override
    public void execute(Game game)
    {
        catDog.setVelocity(velocity);
    }
}
