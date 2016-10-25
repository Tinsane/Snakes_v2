package Core.RecordsContainer;

import java.util.*;
import java.util.Map.Entry;

/**
 * Created by ISmir on 25.10.2016.
 */

//TODO: write tests
public class RecordsContainer
{
    public static final int maximalRecordsCount = 10;
    private HashMap<String, Integer> records = new HashMap<>();

    RecordsContainer()
    {

    }

    public RecordsContainer(HashMap<String, Integer> records)
    {
        this.records = records;
    }

    private Entry<String, Integer> getMinimalRecord()
    {
        Entry<String, Integer> minimalRecord = null;
        for (Entry<String, Integer> entry : records.entrySet())
        {
            if (minimalRecord == null || entry.getValue() < minimalRecord.getValue())
                minimalRecord = entry;
        }
        return minimalRecord;
    }

    private int getMinimalRecordValue()
    {
        Entry<String, Integer> minimalRecord = getMinimalRecord();
        return minimalRecord == null ? Integer.MAX_VALUE : minimalRecord.getValue();
    }

    private void deleteMinimalRecord()
    {
        Entry<String, Integer> minimalRecord = getMinimalRecord();
        if (minimalRecord != null)
            records.remove(minimalRecord.getKey());
    }

    private void addRecordWithoutCheck(String playerName, int playerScore)
    {
        records.put(playerName, playerScore);
        if (records.size() > 10)
            deleteMinimalRecord();
    }

    boolean tryAddRecord(String playerName, int playerScore)
    {
        if (!possibleToAddRecord(playerScore))
            return false;
        addRecordWithoutCheck(playerName, playerScore);
        return true;
    }

    public boolean possibleToAddRecord(int score)
    {
        return records.size() < 10 || getMinimalRecordValue() < score;
    }

    public int count()
    {
        return records.size();
    }

    public void addRecord(String playerName, int playerScore)
    {
        tryAddRecord(playerName, playerScore);
    }

    public Set<Entry<String,Integer>> getRecords() //TODO: sort them
    {
        return records.entrySet();
    }
}
