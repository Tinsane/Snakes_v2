package Tests;

import Core.Game.Game;
import Core.Game.GameCreator;
import org.junit.Test;

import java.io.*;

/**
 * Created by Владимир on 09.10.2016.
 */
public class SerializationTest
{
    @Test
    public void testSerializationOutput() throws IOException
    {
        FileOutputStream fileOutputStream = new FileOutputStream("test.snake_snapshot");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        GameCreator gameCreator = new GameCreator();
        gameCreator.setMapSize(2, 2);
        gameCreator.placeWall(0, 0);
        gameCreator.placeBlueberry(1, 1);
        Game game = gameCreator.createGame(0, 1, 5);
        objectOutputStream.writeObject(game);
    }

    @Test
    public void testSerializationInput() throws IOException, ClassNotFoundException
    {
        FileInputStream fileInputStream = new FileInputStream("test.snake_snapshot");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Game game = (Game) objectInputStream.readObject();

    }
}
