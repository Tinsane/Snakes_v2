package Core.Game;
/**
 * Created by Владимир on 16.09.2016.
 */

import Core.Game.GameUpdaters.GameUpdater;
import Core.GameCommands.GameCommand;
import Core.GameObjects.GameObject;
import Core.GameObjects.Snake.Snake;
import Core.MapObjects.DynamicMapObjects.SnakeCell;
import Core.MapObjects.MapObject;
import Core.MapObjects.StaticMapObjects.Berries.Berry;
import Core.MapObjects.StaticMapObjects.EmptyCell;
import Core.MapObjects.StaticMapObjects.Wall;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Game extends AbstractGame implements Serializable, Cloneable
{
    public ArrayList<GameObject> gameObjects;
    private GameUpdater gameUpdater;
    private LinkedList<MapObject[][]> maps;

    public boolean isFinished()
    {
        return gameObjects.stream().anyMatch(GameObject::getIsDestructed);
    } // TODO: let gameUpdater decide when game finishes

    public Game(MapObject[][] map, ArrayList<GameObject> gameObjects, GameUpdater gameUpdater)
    {
        maps = new LinkedList<>();
        maps.addFirst(map);
        this.gameObjects = gameObjects;
        gameUpdater.setGame(this);
        this.gameUpdater = gameUpdater;
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
    public ArrayList<GameObject> getGameObjects()
    {
        return gameObjects;
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

    public void rollback(int turnsNumber)
    {
        for (int i = 0; i < turnsNumber && maps.size() > 1; ++i)
            maps.removeFirst();
    }

    public void update()
    {
        if (isFinished())
            throw new UnsupportedOperationException("Game finished. Impossible to update.");
        maps.addFirst(gameUpdater.getUpdatedMap());
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

    public int getAliveObjectIndex()
    {
        for(int i = 0; i < gameObjects.size(); ++i)
            if (!gameObjects.get(i).getIsDestructed())
                return i;
        return -1;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        MapObject[][] map = getCurrentMap();

        for (int i = 0; i < getWidth(); ++i)
        {
            for (int j = 0; j < getHeight(); ++j)
                if (map[i][j] instanceof Wall)
                    builder.append('#');
                else if (map[i][j] instanceof EmptyCell)
                    builder.append('.');
                else if (map[i][j] instanceof Berry)
                    builder.append('B');
                else if (map[i][j] instanceof SnakeCell)
                    builder.append('S');
            builder.append('\n');
        }
        return builder.toString();
    }
}
