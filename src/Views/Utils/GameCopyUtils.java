package Views.Utils;

import Core.Game.Game;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;

/**
 * Created by ISmir on 21.11.2016.
 */
public class GameCopyUtils
{
    public static Game CopyGame(Game game)
    {
        return fromString(toString(game));
    }

    private static Game fromString( byte[] data )
    {
        try (ByteArrayInputStream byteArrayStream = new ByteArrayInputStream(data);
                ObjectInputStream objectStream = new ObjectInputStream(byteArrayStream))
        {
            return Game.loadGame(objectStream);
        } catch (IOException | ClassNotFoundException e)
        {
            throw new NotImplementedException();
        }
    }

    /** Write the object to a Base64 string. */
    private static byte[] toString( Serializable o )
    {
        ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();
        try (ObjectOutputStream objectStream = new ObjectOutputStream( byteArrayStream ))
        {
            objectStream.writeObject(o);
        } catch (IOException e)
        {
            throw new NotImplementedException();
        }
        return byteArrayStream.toByteArray();
    }
}
