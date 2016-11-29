package Core.GameUpdatingSystem.GameUpdaters;

import AI.BaseAI;
import Core.Game.Game;

import java.util.ArrayList;

/**
 * Created by Владимир on 28.11.2016.
 */
public class AIGameUpdater extends BaseGameUpdater
{
    private BaseAI[] AIs;
    public AIGameUpdater(BaseAI[] AIs)
    {
        super(0);
        this.AIs = AIs;
    }

    @Override
    public void execute(Game game)
    {
        for(BaseAI baseAI : AIs)
            baseAI.Order(game);
    }
}
