package Views.Utils;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Владимир on 20.10.2016.
 */
public class ParentFrameRestorer extends WindowAdapter
{
    private final JFrame frame;
    private final JFrame parentFrame;
    public ParentFrameRestorer(JFrame frame, JFrame parentFrame)
    {
        this.frame = frame;
        this.parentFrame = parentFrame;
    }
    @Override
    public void windowClosing(WindowEvent e)
    {
        super.windowClosing(e);
        frame.dispose();
        parentFrame.setVisible(true);
        parentFrame.setAlwaysOnTop(true);
    }
}
