package Game; /**
 * Created by Владимир on 16.09.2016.
 */

import GameCommands.GameCommand;
import MapObjects.DynamicMapObjects.SnakeCell;
import MapObjects.MapObject;
import MapObjects.StaticMapObjects.Berries.Blueberry;
import MapObjects.StaticMapObjects.Berries.Strawberry;
import Snake.Snake;
import Utils.IntPair;
import Utils.VelocityVector;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Random;

public class Game
{
    private static final int DEFAULT_UPDATE_DELAY = 300;
    private final int UPDATE_DELAY;
    public Snake snake;
    private LinkedList<MapObject[][]> maps;
    private Timer gameTimer;
    private Random gameRandom;

    public Game(MapObject[][] map, Snake snake)
    {
        this(map, snake, DEFAULT_UPDATE_DELAY);
    }

    public Game(MapObject[][] map, Snake snake, int updateDelay)
    {
        maps = new LinkedList<>();
        maps.addFirst(map);
        this.snake = snake;
        gameRandom = new Random();
        UPDATE_DELAY = updateDelay;
    }

    private static void clearDestructedObjects(MapObject[][] map)
    {
        for (int x = 0; x < map.length; ++x)
            for (int y = 0; y < map[0].length; ++y)
                if (map[x][y] != null && map[x][y].getIsDestructed())
                    map[x][y] = null;
    }

    public void executeCommand(GameCommand command)
    {
        command.execute(this);
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
                    map[x][y] = gameRandom.nextBoolean() ? new Blueberry() : new Strawberry();
                if (map[x][y] == null)
                    --berryCellNumber;
            }
    }

    private void moveSnake(MapObject[][] newMap)
    {
        MapObject[][] curMap = getCurrentMap();
        IntPair coordinates = snake.head.getCoordinates(curMap);
        while (coordinates != null)
        {
            moveObject(newMap, coordinates.x, coordinates.y);
            SnakeCell current = (SnakeCell) curMap[coordinates.x][coordinates.y];
            coordinates = SnakeCell.getPreviousCoordinates(curMap, coordinates);
            SnakeCell previous = current.previous;
            if (previous != null)
                previous.setVelocity(current.getVelocity());
        }
    }

    private void moveObject(MapObject[][] newMap, int x, int y)
    {
        MapObject[][] curMap = getCurrentMap();
        MapObject curObject = curMap[x][y];
        VelocityVector velocity = curObject.getVelocity();
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

    public void update()
    {
        MapObject[][] curMap = maps.peekFirst();
        MapObject[][] newMap = new MapObject[curMap.length][curMap[0].length];
        for (int x = 0; x < curMap.length; ++x)
            for (int y = 0; y < curMap[0].length; ++y)
            {
                MapObject curObject = curMap[x][y];
                if (!(curObject == null || curObject.getIsDestructed() || curObject.getClass() == SnakeCell.class))
                    moveObject(newMap, x, y);
            }
        moveSnake(newMap);
        clearDestructedObjects(newMap);
        maps.addFirst(newMap);
        if (snake.getIsDestructed())
            stop();
    }
}
