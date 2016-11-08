package Views.RecordsView;

import Core.RecordsContainer.RecordsContainer;
import Core.RecordsContainer.RecordsLoader;
import Views.Utils.ButtonUtils.MenuButton;
import Views.Utils.PanelUtils.MenuPanel;

import javax.swing.*;
import java.util.HashMap;

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
        records.addRecord("Kekmaster", 91); //TODO: delete
        records.addRecord("Van", 84); //TODO: delete

        setTitle("SnakeRecords");

        setSize(400, 400);

        add(new MenuPanel(records.getRecords().stream()
                .map(record -> new MenuButton(String.format("%s: %d", record.getKey(), record.getValue()), 45))
                .toArray(MenuButton[]::new)));
        setVisible(true);
    }
}
