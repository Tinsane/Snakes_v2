package Core.RecordsContainer;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by ISmir on 25.10.2016.
 */
public class RecordsSaver
{
    public static void saveRecords(RecordsContainer container)
    {
        saveRecords(container, RecordsLoader.defaultPath);
    }

    public static void saveRecords(RecordsContainer container, String path)
    {
        throw new NotImplementedException(); // TODO: write just as records loader
    }
}
