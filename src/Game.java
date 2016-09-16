/**
 * Created by Владимир on 16.09.2016.
 */

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.*;
import java.util.LinkedList;

public class Game
{
    private final int UPDATE_DELAY = 300;
    public LinkedList<IMapObject[][]> maps;
    public Snake snake;
    private static IMapObject[][] loadMap(String fileName)
    {
        throw new NotImplementedException();
    }
    public Game(String levelFileName)
    {
        maps = new LinkedList<>();
        maps.addFirst(loadMap(levelFileName));
        snake = new Snake();
    }
    public void start()
    {
        Timer timer = new Timer(UPDATE_DELAY, e -> update());
        timer.start();
    }
    private void update()
    {
        IMapObject[][] curMap = maps.peekFirst();
        IMapObject[][] newMap = new IMapObject[curMap.length][curMap[0].length];
        for(int x = 0; x < curMap.length; ++x)
            for(int y = 0; y < curMap[0].length; ++y)
            {
                IMapObject curObject = curMap[x][y];
                if (curObject == null || curObject.getIsDestructed())
                    continue;
                Vector velocity = curObject.getVelocity();
                int newX = x + velocity.x, newY = y + velocity.y;
                if (curMap[newX][newY] != null && curMap[newX][newY] != curObject &&
                        curMap[newX][newY].getVelocity() == velocity.getReversed())
                {
                    curObject.processCollision(curMap[newX][newY]);
                    if (curObject.getIsDestructed())
                        continue;
                }
                if (newMap[newX][newY] != null)
                    curObject.processCollision(newMap[newX][newY]);
                if (!curObject.getIsDestructed())
                    newMap[newX][newY] = curObject;
            }
        maps.addFirst(newMap);
    }
}
