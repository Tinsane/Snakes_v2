/**
 * Created by Владимир on 16.09.2016.
 */

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.*;
import java.util.LinkedList;

public class Game
{
    private final int UPDATE_DELAY = 300;
    public LinkedList<MapObject[][]> maps;
    public Snake snake;
    private static MapObject[][] loadMap(String fileName)
    {
        throw new NotImplementedException();
    }

    public Game(String levelFileName)
    {
        maps = new LinkedList<>();
        maps.addFirst(loadMap(levelFileName));
        snake = new Snake();
    }

    public void rollback(int turnsNumber)
    {
        throw new NotImplementedException();
    }

    public void start()
    {
        Timer timer = new Timer(UPDATE_DELAY, e -> update());
        timer.start();
    }

    private void update()
    {
        MapObject[][] curMap = maps.peekFirst();
        MapObject[][] newMap = new MapObject[curMap.length][curMap[0].length];
        for(int x = 0; x < curMap.length; ++x)
            for(int y = 0; y < curMap[0].length; ++y)
            {
                MapObject curObject = curMap[x][y];
                if (curObject == null || curObject.getIsDestructed())
                    continue;
                Vector velocity = curObject.getVelocity();
                int newX = x + velocity.x, newY = y + velocity.y; // smirnov: better to split into 2 strings
                if (curMap[newX][newY] != null && curMap[newX][newY] != curObject && // smirnov: better to create a function fot checking this
                        curMap[newX][newY].getVelocity() == velocity.getReversed()) // smirnov: and change it for new solution
                {
                    curObject.processCollision(curMap[newX][newY], this);
                    if (curObject.getIsDestructed())
                        continue;
                }
                if (newMap[newX][newY] != null)
                    curObject.processCollision(newMap[newX][newY], this);
                if (!curObject.getIsDestructed())
                    newMap[newX][newY] = curObject;
            }
        maps.addFirst(newMap);
    }
}
