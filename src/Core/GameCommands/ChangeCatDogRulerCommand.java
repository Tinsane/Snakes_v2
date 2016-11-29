package Core.GameCommands;

import Core.Game.Game;
import Core.GameObjects.CatDog;
import Core.Utils.VelocityVector;

/**
 * Created by Владимир on 28.11.2016.
 */
public class ChangeCatDogRulerCommand implements GameCommand
{
    private final CatDog catDog;

    public ChangeCatDogRulerCommand(CatDog catDog)
    {
        this.catDog = catDog;
    }

    @Override
    public void execute(Game game)
    {
        catDog.setVelocity(VelocityVector.zero, game.getCurrentMap());
        catDog.tailRules = !catDog.tailRules;
    }
}
