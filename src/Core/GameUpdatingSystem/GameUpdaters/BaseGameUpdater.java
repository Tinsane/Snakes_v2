package Core.GameUpdatingSystem.GameUpdaters;

import Core.GameCommands.GameCommand;

import java.io.Serializable;

/**
 * Created by Владимир on 28.11.2016.
 */
public abstract class BaseGameUpdater implements GameCommand, Serializable
{
    public final Integer updatePriority;

    protected BaseGameUpdater(int updatePriority)
    {
        this.updatePriority = updatePriority;
    }
}
