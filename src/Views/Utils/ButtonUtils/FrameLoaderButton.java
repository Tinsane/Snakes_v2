package Views.Utils.ButtonUtils;

import Views.Utils.ParentFrameRestorer;

import javax.swing.*;

/**
 * Created by Владимир on 31.10.2016.
 */
public abstract class FrameLoaderButton extends MenuButton
{
    public FrameLoaderButton(String text, int fontSize, JFrame parent)
    {
        super(text, fontSize);
        addActionListener(e -> loadFrame(parent));
    }

    private void loadFrame(JFrame parent)
    {
        JFrame frame = createFrame(parent);
        if (frame == null)
            return; // in case user changes mind and doesn't open frame, for example, when he has to choose file.
        parent.setVisible(false);
        frame.addWindowListener(new ParentFrameRestorer(frame, parent));
    }

    protected abstract JFrame createFrame(JFrame parent);
}
