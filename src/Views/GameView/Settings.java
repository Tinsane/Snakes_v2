package Views.GameView;

import Views.Styles.GameStyle;

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
    public double speedUp = 1;

    public int gameUpdateFrequency = 5;
    public int updateInterval = (int)Math.round(DEFAULT_UPDATE_INTERVAL / speedUp / gameUpdateFrequency);
    public GameStyle style;

    public Settings(GameStyle style)
    {
        this.style = style;
    }
}
