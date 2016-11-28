package AI;

import Core.Game.Game;

import java.io.Serializable;

/**
 * Created by Владимир on 27.11.2016.
 */
public abstract class BaseAI implements Serializable
{
    protected final Game game;
    BaseAI(Game game)
    {
        this.game = game;
    }
    public abstract void updateStrategy();
}
