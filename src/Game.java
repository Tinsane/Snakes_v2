/**
 * Created by Владимир on 16.09.2016.
 */

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Random;

public class Game
{
    public static class GameCreator
    {
        private static MapObject[][] loadMap(String fileName)
        {
            throw new NotImplementedException();
        }

        public Game createGame(MapObject[][] map, Snake snake)
        {
            return new Game(map, snake);
        }

        public Game createGame(MapObject[][] map, Vector snakePosition)
        {
            if (!(0 <= snakePosition.x && snakePosition.x < map.length) ||
                    !(0 <= snakePosition.y && snakePosition.y < map[0].length) ||
                    map[snakePosition.x][snakePosition.y] != null)
                throw new IllegalArgumentException("Invalid snake position!");
            SnakeCell cell = new SnakeCell();
            map[snakePosition.x][snakePosition.y] = cell;
            Snake snake = new Snake(cell, cell);
            return new Game(map, snake);
        }
    }
    private final int UPDATE_DELAY = 300;
    private LinkedList<MapObject[][]> maps;
    public Snake snake;
    private Timer gameTimer;
    private Random gameRandom;

    private Game(MapObject[][] map, Snake snake)
    {
        maps = new LinkedList<>();
        maps.addFirst(map);
        this.snake = snake;
        gameRandom = new Random();
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
                    map[x][y] = gameRandom.nextBoolean() ? new Blueberry() : new Strawberry();
                if (map[x][y] == null)
                    --berryCellNumber;
            }
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
                int newX = x + velocity.x;
                int newY = y + velocity.y;
                if (newMap[newX][newY] != null)
                    curObject.processCollision(newMap[newX][newY], this);
                if (!curObject.getIsDestructed())
                    newMap[newX][newY] = curObject;
            }
        clearDestructedObjects(newMap);
        maps.addFirst(newMap);
        if (snake.getIsDestructed())
            stop();
    }
}
