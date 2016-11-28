package Core.GameUpdatingSystem;

import Core.Game.Game;
import Core.GameUpdatingSystem.GameUpdaters.BaseGameUpdater;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Владимир on 28.11.2016.
 */
public class GameUpdatingSystem implements Serializable
{
    public ArrayList<BaseGameUpdater> gameUpdaters;
    public GameUpdatingSystem(ArrayList<BaseGameUpdater> gameUpdaters)
    {
        this.gameUpdaters = gameUpdaters;
        this.gameUpdaters.sort((BaseGameUpdater first, BaseGameUpdater second) ->
                first.updatePriority.compareTo(second.updatePriority));
    }
    public void updateGame(Game game)
    {
        gameUpdaters.forEach(game::executeCommand);
    }
}
