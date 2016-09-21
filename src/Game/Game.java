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

import javax.swing.*;
import java.util.LinkedList;
import java.util.Random;

public class Game
{
    private static final int DEFAULT_UPDATE_DELAY = 300;
    public Snake snake;
    private LinkedList<MapObject[][]> maps;
    private Timer gameTimer;
    private Random gameRandom;

    Game(MapObject[][] map, Snake snake)
    {
        this(map, snake, DEFAULT_UPDATE_DELAY);
    }

    Game(MapObject[][] map, Snake snake, int updateDelay)
    {
        maps = new LinkedList<>();
        maps.addFirst(map);
        this.snake = snake;
        gameRandom = new Random();
        gameTimer = new Timer(updateDelay, e -> update());
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

    private void placeObject(MapObject[][] map, MapObject object, IntPair position)
    {
        if (map[position.x][position.y] != null)
            object.processCollision(map[position.x][position.y], this);
        if (!object.getIsDestructed())
            map[position.x][position.y] = object;
    }

    private void moveSnakeCell(MapObject[][] newMap, SnakeCell cell, IntPair cellPosition)
    {
        if (cell.previous != null)
        {
            IntPair previousPosition = SnakeCell.getPreviousCoordinates(getCurrentMap(), cellPosition);
            if (previousPosition == null)
                placeObject(newMap, cell.previous, cellPosition);
            else
                moveSnakeCell(newMap, cell.previous, previousPosition);
            cell.previous.setVelocity(cell.getVelocity());
        }
        moveObject(newMap, cellPosition);
    }

    private void moveSnake(MapObject[][] newMap)
    {
        moveSnakeCell(newMap, snake.head, snake.head.getCoordinates(getCurrentMap()));
    }

    private void moveObject(MapObject[][] newMap, IntPair position)
    {
        MapObject[][] curMap = getCurrentMap();
        MapObject curObject = curMap[position.x][position.y];
        IntPair newPosition = position.getAdded(curObject.getVelocity().getIntPair());
        placeObject(newMap, curObject, newPosition);
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
                    moveObject(newMap, new IntPair(x, y));
            }
        moveSnake(newMap);
        clearDestructedObjects(newMap);
        maps.addFirst(newMap);
        if (snake.getIsDestructed())
            stop();
    }
}
