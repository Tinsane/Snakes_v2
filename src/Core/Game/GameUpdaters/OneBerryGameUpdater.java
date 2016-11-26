package Core.Game.GameUpdaters;

import Core.Game.Game;
import Core.MapObjects.MapObject;
import Core.MapObjects.StaticMapObjects.Berries.Berry;
import Core.MapObjects.StaticMapObjects.Berries.Blueberry;
import Core.MapObjects.StaticMapObjects.Berries.Strawberry;
import Core.MapObjects.StaticMapObjects.EmptyCell;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by ISmir on 26.11.2016.
 */
public class OneBerryGameUpdater extends BaseGameUpdater
{
    private void generateBerry()
    {
        Random updaterRandom = new Random();
        int freeCellsCnt = 0;
        for (MapObject[] row : newMap)
            for (MapObject cell : row)
                if (cell instanceof EmptyCell)
                    ++freeCellsCnt;
        if (freeCellsCnt == 0)
            return;
        int berryCellNumber = updaterRandom.nextInt(freeCellsCnt);
        outerLoop:
        for (int x = 0; x < newMap.length; ++x)
            for (int y = 0; y < newMap[0].length; ++y)
            {
                if (!(newMap[x][y] instanceof EmptyCell))
                    continue;
                if (berryCellNumber == 0)
                {
                    newMap[x][y] = updaterRandom.nextBoolean() ? new Blueberry() : new Strawberry();
                    break outerLoop;
                }
                --berryCellNumber;
            }
    }

    private boolean newMapContainsBerry()
    {
        for(MapObject[] row : newMap)
            if (Arrays.stream(row).anyMatch(cell -> cell instanceof Berry))
                return true;
        return false;
    }

    @Override
    public MapObject[][] getUpdatedMap()
    {
        if (game == null)
            throw new IllegalStateException("Game unset.");
        updateGame();
        if (!newMapContainsBerry())
            generateBerry();
        return newMap;
    }
}
