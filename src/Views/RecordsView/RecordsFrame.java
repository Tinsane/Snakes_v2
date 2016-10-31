package Views.RecordsView;

import Core.RecordsContainer.RecordsContainer;
import Core.RecordsContainer.RecordsLoader;
import Views.Styles.MenuStyle;
import Views.Utils.MenuButton;
import Views.Utils.ParentFrameRestorer;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ISmir on 25.10.2016.
 */
public class RecordsFrame extends JFrame
{
    RecordsContainer records;

    public RecordsFrame()
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
        panel.setBackground(MenuStyle.backgroundColor);
        panel.setLayout(new GridLayout(records.count(), 1));

        for (Map.Entry<String, Integer> record :
                records.getRecords())
        {
            panel.add(new MenuButton(String.format("%s: %d", record.getKey(), record.getValue()), 45));
        }

        setVisible(true);
    }
}
