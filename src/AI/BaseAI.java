package AI;

import Core.Game.Game;

import java.io.Serializable;

/**
 * Created by Владимир on 27.11.2016.
 */
public abstract class BaseAI implements Serializable
{
    BaseAI()
    {
    }
    public abstract void Order(Game game);
}
