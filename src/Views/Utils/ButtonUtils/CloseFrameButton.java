package Views.Utils.ButtonUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

/**
 * Created by Владимир on 02.11.2016.
 */
public class CloseFrameButton extends MenuButton
{
    protected JFrame frame;
    public CloseFrameButton(String text, int fontSize, JFrame frame)
    {
        super(text, fontSize);
        this.frame = frame;
        addActionListener(this::onClose);
    }

    protected void onClose(ActionEvent e)
    {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}
