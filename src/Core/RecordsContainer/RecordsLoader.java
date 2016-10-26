package Core.RecordsContainer;

import javafx.util.Pair;

import java.util.Scanner;

/**
 * Created by ISmir on 25.10.2016.
 */
public class RecordsLoader
{
    public static final String defaultPath = "records.txt";

    public static RecordsContainer loadRecords()
    {
        return loadRecords(defaultPath);
    }

    public static RecordsContainer loadRecords(String filename)
    {
        Scanner scanner = new Scanner(filename);
        return loadRecords(scanner); // TODO: should check if file exists, if it doesn't - create
    }

    private static RecordsContainer loadRecords(Scanner scanner)
    {
        RecordsContainer records = new RecordsContainer();
        while (scanner.hasNextLine())
        {
            String record = scanner.nextLine();
            if (record.isEmpty())
                continue;
            try
            {
                Pair<String, Integer> recordParsed = parseLine(record);
                if (!records.tryAddRecord(recordParsed.getKey(), recordParsed.getValue()))
                    continue; // TODO: log that error occurred
            }
            catch (IllegalArgumentException ex)
            {
                // TODO: log that error occurred
            }
        }
        return records;
    }

    private static Pair<String, Integer> parseLine(String line) throws IllegalArgumentException
    {
        String[] record = line.split(" ");
        if (record.length != 2)
            throw new IllegalArgumentException();
        return new Pair<>(record[0], Integer.parseInt(record[1]));
    }
}
