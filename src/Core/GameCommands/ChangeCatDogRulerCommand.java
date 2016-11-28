package Core.GameCommands;

import Core.Game.Game;
import Core.GameObjects.CatDog;

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
        catDog.tailRules = !catDog.tailRules;
    }
}
