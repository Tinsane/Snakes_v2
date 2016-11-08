package Core.Game;

import Core.MapObjects.MapObject;
import Core.Snake.Snake;

/**
 * Created by ISmir on 23.10.2016.
 */
public interface GameAlike
{
    MapObject[][] getCurrentMap();

    Snake getSnake();

    int getWidth();
    int getHeight();
}
