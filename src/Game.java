/**
 * Created by Владимир on 16.09.2016.
 */

import javafx.util.Pair;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

public class Game
{
    private final int UPDATE_DELAY = 300;
    public LinkedList<MapObject[][]> maps;
    public Snake snake;
    private Timer gameTimer;
    private Random gameRandom;
    private static MapObject[][] loadMap(String fileName)
    {
        throw new NotImplementedException();
    }

    public Game(String levelFileName)
    {
        maps = new LinkedList<>();
        maps.addFirst(loadMap(levelFileName));
        snake = new Snake();
        gameRandom = new Random();
    }

    public MapObject[][] getCurrentMap()
    {
        return maps.peekFirst();
    }

    public void rollback(int turnsNumber)
    {
        for (int i = 0; i < turnsNumber && maps.size() > 1; ++i)
            maps.removeFirst();
    }

    public void start()
    {
        gameTimer = new Timer(UPDATE_DELAY, e -> update());
        gameTimer.start();
    }

    public void stop()
    {
        gameTimer.stop();
    }

    private static void clearDestructedObjects(MapObject[][] map)
    {
        for (int x = 0; x < map.length; ++x)
            for (int y = 0; y < map[0].length; ++y)
                if (map[x][y] != null && map[x][y].getIsDestructed())
                    map[x][y] = null;
    }

    private void generateBerry(MapObject[][] map)
    {
        int freeCellsCnt = 0;
        for (MapObject[] row : map)
            for (MapObject cell : row)
                if (cell == null)
                    ++freeCellsCnt;
        if (freeCellsCnt == 0)
            return;
        int berryCellNumber = gameRandom.nextInt(freeCellsCnt);
        for (int x = 0; x < map.length; ++x)
            for (int y = 0; y < map[0].length; ++y)
            {
                if (berryCellNumber == 0)
                    map[x][y] = new Berry(); // TODO: satisfactionCoefficient?
                if (map[x][y] == null)
                    --berryCellNumber;
            }
    }

    private void moveSnake(MapObject[][] newMap)
    {
        MapObject[][] curMap = getCurrentMap();
        Pair<Integer, Integer> currentCoordinates = snake.head.getCoordinates(curMap);
        while (currentCoordinates != null)
        {
            int currentX = currentCoordinates.getKey();
            int currentY = currentCoordinates.getValue();
            moveObject(newMap, currentX, currentY);
            SnakeCell current = (SnakeCell) curMap[currentX][currentY];
            currentCoordinates = SnakeCell.getPreviousCoordinates(curMap, currentX, currentY);
            SnakeCell previous = current.getPrevious();
            if (previous != null)
                previous.setVelocity(current.getVelocity());
        }
    }

    private void moveObject(MapObject[][] newMap, int x, int y)
    {
        MapObject[][] curMap = getCurrentMap();
        MapObject curObject = curMap[x][y];
        Vector velocity = curObject.getVelocity();
        int newX = x + velocity.x;
        int newY = y + velocity.y;
        // no need now
        /*if (curMap[newX][newY] != null && curMap[newX][newY] != curObject && // smirnov: better to create a function fot checking this
                curMap[newX][newY].getVelocity() == velocity.getReversed()) // smirnov: and change it for new solution
        {
            curObject.processCollision(curMap[newX][newY], this);
            if (curObject.getIsDestructed())
                continue;
        }*/
        if (newMap[newX][newY] != null)
            curObject.processCollision(newMap[newX][newY], this);
        if (!curObject.getIsDestructed())
            newMap[newX][newY] = curObject;
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
                if (curObject.getClass() == SnakeCell.class)
                    continue;
                moveObject(newMap, x, y);
            }
        moveSnake(newMap);
        clearDestructedObjects(newMap);
        maps.addFirst(newMap);
        if (snake.getIsDestructed())
            stop();
    }
}
