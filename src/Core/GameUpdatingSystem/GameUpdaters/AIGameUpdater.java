package Core.GameUpdatingSystem.GameUpdaters;

import AI.BaseAI;
import Core.Game.Game;

import java.util.ArrayList;

/**
 * Created by Владимир on 28.11.2016.
 */
public class AIGameUpdater extends BaseGameUpdater
{
    private ArrayList<BaseAI> AIs;
    public AIGameUpdater(ArrayList<BaseAI> AIs)
    {
        super(0);
        this.AIs = AIs;
    }

    @Override
    public void execute(Game game)
    {
        AIs.forEach(BaseAI::updateStrategy);
    }
}
