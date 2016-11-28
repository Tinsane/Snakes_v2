package Views.GameView;

import Controllers.MovementBinds;
import Core.GameUpdatingSystem.GameUpdaters.BaseGameUpdater;
import Core.GameUpdatingSystem.GameUpdaters.GameMovementUpdater;
import Core.GameUpdatingSystem.GameUpdaters.OneBerryGameUpdater;
import Core.GameUpdatingSystem.GameUpdatingSystem;
import Views.Styles.GameStyle;
import Views.Styles.MultiplayerGameStyle;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by ISmir on 09.10.2016.
 */
/*
There's a problem.
What if somebody gives settings to Frame and then changes settings.
How does Frame know about changes.
If game will use these settings not only while initializing - may occur bad situation.

Maybe a good solution would be to copy settings while initializing game.
 */
public class Settings
{
    private static final int DEFAULT_UPDATE_INTERVAL = 200;
    public static final int ARROWS_CONTROL = 0;
    public static final int WASD_CONTROL = 1;

    public double speedUp = 1;

    public int gameUpdateFrequency = 5;
    public int updateInterval = (int)Math.round(DEFAULT_UPDATE_INTERVAL / speedUp / gameUpdateFrequency);
    public GameStyle singlePlayerStyle;
    public MultiplayerGameStyle multiplayerStyle;
    public ArrayList<MovementBinds> movementControls;
    public GameUpdatingSystem gameUpdatingSystem;

    public Settings(GameStyle singlePlayerStyle, MultiplayerGameStyle multiplayerStyle)
    {
        ArrayList<BaseGameUpdater> gameUpdaters = new ArrayList<>();
        gameUpdaters.add(new GameMovementUpdater());
        gameUpdaters.add(new OneBerryGameUpdater());
        gameUpdatingSystem = new GameUpdatingSystem(gameUpdaters);
        this.singlePlayerStyle = singlePlayerStyle;
        this.multiplayerStyle = multiplayerStyle;
        movementControls = new ArrayList<>();
        movementControls.add(new MovementBinds(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT));
        movementControls.add(new MovementBinds(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_A));
    }
}
