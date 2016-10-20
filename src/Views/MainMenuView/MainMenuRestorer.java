package Views.MainMenuView;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Владимир on 20.10.2016.
 */
public class MainMenuRestorer extends WindowAdapter
{
    private final JFrame frame;
    private final Frame mainMenuFrame;
    public MainMenuRestorer(JFrame frame, Frame mainMenuFrame)
    {
        this.frame = frame;
        this.mainMenuFrame = mainMenuFrame;
    }
    @Override
    public void windowClosing(WindowEvent e)
    {
        super.windowClosing(e);
        frame.dispose();
        mainMenuFrame.setVisible(true);
    }
}
