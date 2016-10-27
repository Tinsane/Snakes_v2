package Views.RecordsView;

import Core.RecordsContainer.RecordsContainer;
import Core.RecordsContainer.RecordsLoader;
import Views.MainMenuView.MainMenuRestorer;
import Views.Utils.TextButton;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ISmir on 25.10.2016.
 */
public class RecordsFrame extends JFrame
{
    public static final Font font = new Font("Tahoma", Font.BOLD, 45);;
    RecordsContainer records;

    public RecordsFrame(Views.MainMenuView.Frame mainMenuFrame)
    {
        super();
        records = RecordsLoader.loadRecords();

        records = new RecordsContainer(new HashMap<>()); //TODO: delete
        records.addRecord("Van", 84); //TODO: delete
        records.addRecord("Kekmaster", 85); //TODO: delete

        setTitle("SnakeRecords");

        setSize(400, 400);

        JPanel panel = new JPanel();
        add(panel);
        panel.setBackground(Color.GREEN);
        panel.setLayout(new GridLayout(records.count(), 1));

        for (Map.Entry<String, Integer> record :
                records.getRecords())
        {
            panel.add(new TextButton(String.format("%s: %d", record.getKey(), record.getValue()), font, Color.YELLOW, new Color(255, 215, 0)));
        }

        addWindowListener(new MainMenuRestorer(this, mainMenuFrame));

        setVisible(true);
    }
}
