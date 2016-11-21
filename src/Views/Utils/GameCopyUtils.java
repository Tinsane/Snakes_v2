package Views.Utils;

import Core.Game.Game;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;
import java.util.Base64;

/**
 * Created by ISmir on 21.11.2016.
 */
public class SerializationUtils
{
    private static Game fromString( String s )
    {
        byte [] data = Base64.getDecoder().decode( s );
        try (ByteArrayInputStream byteArrayStream = new ByteArrayInputStream(data);
                ObjectInputStream objectStream = new ObjectInputStream(byteArrayStream))
        {
            return Game.loadGame(objectStream);
        } catch (IOException | ClassNotFoundException e)
        {
            throw new NotImplementedException();
        }
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(  data ) );
        Object o  = ois.readObject();
        ois.close();
        return o;
    }

    /** Write the object to a Base64 string. */
    private static String toString( Serializable o )
    {
        ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();
        try (ObjectOutputStream objectStream = new ObjectOutputStream( byteArrayStream ))
        {
            objectStream.writeObject(o);
        } catch (IOException e)
        {
            throw new NotImplementedException();
        }
        return Base64.getEncoder().encodeToString(byteArrayStream.toByteArray());
    }
}
