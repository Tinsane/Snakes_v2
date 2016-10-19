package View;

import View.Styles.GameStyle;

/**
 * Created by ISmir on 09.10.2016.
 */
/*
There's a problem.
What if somebody gives settings to GameView and then changes settings.
How does GameView know about changes.
If game will use these settings not only while initializing - may occur bad situation.

Maybe a good solution would be to copy settings while initializing game.
 */
public class GameViewSettings
{
    private static final int DEFAULT_UPDATE_INTERVAL = 200;

    public int gameUpdateFrequency = 5;
    public int updateInterval = DEFAULT_UPDATE_INTERVAL / gameUpdateFrequency;
    public GameStyle style;

    public GameViewSettings(GameStyle style)
    {
        this.style = style;
    }
}
