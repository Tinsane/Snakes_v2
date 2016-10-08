package Core.Game; /**
 * Created by Владимир on 16.09.2016.
 */

import Core.GameCommands.GameCommand;
import Core.MapObjects.DynamicMapObjects.SnakeCell;
import Core.MapObjects.MapObject;
import Core.MapObjects.StaticMapObjects.Berries.Blueberry;
import Core.MapObjects.StaticMapObjects.Berries.Strawberry;
import Core.Snake.Snake;
import Core.Utils.IntPair;

import javax.naming.OperationNotSupportedException;
import javax.swing.*;
import java.util.LinkedList;
import java.util.Random;

public class Game
{
    public Snake snake;
    private GameUpdater gameUpdater;
    private LinkedList<MapObject[][]> maps;

    private boolean isFinished;

    public boolean isFinished()
    {
        return snake.getIsDestructed();
    }

    Game(MapObject[][] map, Snake snake)
    {
        maps = new LinkedList<>();
        maps.addFirst(map);
        this.snake = snake;
        gameUpdater = new GameUpdater(this);
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

    public void update()
    {
        if (isFinished())
            throw new UnsupportedOperationException("Game finished. Impossible to update.");
        maps.addFirst(gameUpdater.getNewMap());
    }

    private class GameUpdater
    {
        private Game game;
        private Random updaterRandom;
        private MapObject[][] newMap;

        private GameUpdater(Game game)
        {
            this.game = game;
            updaterRandom = new Random();
        }

        private void generateBerry()
        {
            int freeCellsCnt = 0;
            for (MapObject[] row : newMap)
                for (MapObject cell : row)
                    if (cell == null)
                        ++freeCellsCnt;
            if (freeCellsCnt == 0)
                return;
            int berryCellNumber = updaterRandom.nextInt(freeCellsCnt);
            for (int x = 0; x < newMap.length; ++x)
                for (int y = 0; y < newMap[0].length; ++y)
                {
                    if (berryCellNumber == 0)
                        newMap[x][y] = updaterRandom.nextBoolean() ? new Blueberry() : new Strawberry();
                    if (newMap[x][y] == null)
                        --berryCellNumber;
                }
        }

        private void clearDestructedObjects()
        {
            for (int x = 0; x < newMap.length; ++x)
                for (int y = 0; y < newMap[0].length; ++y)
                    if (newMap[x][y] != null && newMap[x][y].getIsDestructed())
                        newMap[x][y] = null;
        }

        private void placeObject(MapObject object, IntPair position)
        {
            if (newMap[position.x][position.y] != null)
            {
                object.processCollision(newMap[position.x][position.y], game);
                if (!newMap[position.x][position.y].getIsDestructed() && !object.getIsDestructed())
                    throw new IllegalArgumentException("Objects can't decide which one stays alive.");
            }
            if (!object.getIsDestructed())
                newMap[position.x][position.y] = object;
        }

        private void moveSnakeCell(SnakeCell cell, IntPair cellPosition)
        {
            if (cell.previous != null)
            {
                IntPair previousPosition = SnakeCell.getPreviousCoordinates(game.getCurrentMap(), cellPosition);
                if (previousPosition == null)
                    placeObject(cell.previous, cellPosition);
                else
                    moveSnakeCell(cell.previous, previousPosition);
                cell.previous.setVelocity(cell.getVelocity());
            }
            moveObject(cellPosition);
        }

        private void moveSnake()
        {
            SnakeCell snakeHead = game.snake.head;
            moveSnakeCell(snakeHead, snakeHead.getCoordinates(getCurrentMap()));
        }

        private void moveObject(IntPair position)
        {
            MapObject[][] curMap = getCurrentMap();
            MapObject curObject = curMap[position.x][position.y];
            IntPair newPosition = position.getAdded(curObject.getVelocity().getIntPair());
            placeObject(curObject, newPosition);
        }

        private MapObject[][] getNewMap()
        {
            MapObject[][] curMap = game.getCurrentMap();
            newMap = new MapObject[curMap.length][curMap[0].length];
            for (int x = 0; x < curMap.length; ++x)
                for (int y = 0; y < curMap[0].length; ++y)
                {
                    MapObject curObject = curMap[x][y];
                    if (!(curObject == null || curObject.getIsDestructed() || curObject.getClass() == SnakeCell.class))
                        moveObject(new IntPair(x, y));
                }
            moveSnake();
            clearDestructedObjects();
            return newMap;
        }
    }
}
