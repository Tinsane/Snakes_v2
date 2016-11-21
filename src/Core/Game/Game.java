package Core.Game;
/**
 * Created by Владимир on 16.09.2016.
 */

import Core.GameCommands.GameCommand;
import Core.MapObjects.DynamicMapObjects.SnakeCell;
import Core.MapObjects.MapObject;
import Core.MapObjects.StaticMapObjects.Berries.Berry;
import Core.MapObjects.StaticMapObjects.Berries.Blueberry;
import Core.MapObjects.StaticMapObjects.Berries.Strawberry;
import Core.MapObjects.StaticMapObjects.EmptyCell;
import Core.Snake.Snake;
import Core.Utils.IntPair;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Game implements Serializable, GameAlike, Cloneable
{
    public ArrayList<Snake> snakes;
    private GameUpdater gameUpdater;
    private LinkedList<MapObject[][]> maps;

    public int getWidth() { return getCurrentMap().length; }

    public int getHeight() { return getCurrentMap()[0].length; }
    public boolean isFinished()
    {
        return snakes.stream().anyMatch(Snake::getIsDestructed);
    }

    Game(MapObject[][] map, ArrayList<Snake> snakes)
    {
        maps = new LinkedList<>();
        maps.addFirst(map);
        this.snakes = snakes;
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

    @Override
    public Game clone()
    {
        try
        {
            return (Game) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            throw new InternalError();
        }
    }

    @Override
    public ArrayList<Snake> getSnakes()
    {
        return snakes;
    }

    public Snake getOwner(SnakeCell cell)
    {
        for (Snake snake : snakes)
            if (snake.contains(cell))
                return snake;
        throw new IllegalArgumentException("Abeyant snake cell.");
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

    public static Game loadGame(String filePath) throws IOException, ClassNotFoundException
    {
        try (FileInputStream stream = new FileInputStream(filePath))
        {
            return loadGame(stream);
        }
    }

    public static Game loadGame(FileInputStream fileInputStream) throws IOException, ClassNotFoundException
    {
        try (ObjectInputStream stream = new ObjectInputStream(fileInputStream))
        {
            return loadGame(stream);
        }
    }

    public static Game loadGame(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException
    {
        return (Game)objectInputStream.readObject();
    }

    public void writeGame(String filePath) throws IOException, ClassNotFoundException
    {
        try (FileOutputStream stream = new FileOutputStream(filePath))
        {
            writeGame(stream);
        }
    }

    public void writeGame(FileOutputStream fileOutputStream) throws IOException, ClassNotFoundException
    {
        try (ObjectOutputStream stream = new ObjectOutputStream(fileOutputStream))
        {
            writeGame(stream);
        }
    }

    public void writeGame(ObjectOutputStream objectInputStream) throws IOException, ClassNotFoundException
    {
        objectInputStream.writeObject(this);
    }

    private class GameUpdater implements Serializable
    {
        private Game game;
        private MapObject[][] newMap;

        private GameUpdater(Game game)
        {
            this.game = game;

        }

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

        private void clearDestructedObjects()
        {
            for (int x = 0; x < newMap.length; ++x)
                for (int y = 0; y < newMap[0].length; ++y)
                    if (newMap[x][y] != null && newMap[x][y].getIsDestructed())
                        newMap[x][y] = null;
        }

        private void fillEmptyCells()
        {
            for (int x = 0; x < newMap.length; ++x)
                for (int y = 0; y < newMap[0].length; ++y)
                    if (newMap[x][y] == null)
                        newMap[x][y] = new EmptyCell();
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

        private void moveSnakes()
        {
            for (Snake snake : snakes)
            {
                SnakeCell snakeHead = snake.head;
                moveSnakeCell(snakeHead, snakeHead.getCoordinates(getCurrentMap()));
            }
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
                    if (!(curObject.getIsDestructed() ||
                            curObject.getClass() == SnakeCell.class || curObject.getClass() == EmptyCell.class))
                        moveObject(new IntPair(x, y));
                }
            moveSnakes();
            clearDestructedObjects();
            fillEmptyCells();
            boolean doesMapContainBerries = false;
            for(MapObject[] row : newMap)
                for(MapObject cell : row)
                    if (cell instanceof Berry)
                        doesMapContainBerries = true;
            if (!doesMapContainBerries)
                generateBerry();
            return newMap;
        }
    }
}
