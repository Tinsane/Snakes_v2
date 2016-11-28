package AI;

import Core.Game.Game;

/**
 * Created by Владимир on 27.11.2016.
 */
public abstract class BaseAI
{
    protected final Game game;
    BaseAI(Game game)
    {
        this.game = game;
    }
    public abstract void updateStrategy();
}
