package Views.Utils;

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
        parent.setVisible(false);
        JFrame frame = createFrame(parent);
        frame.addWindowListener(new ParentFrameRestorer(frame, parent));
    }

    protected abstract JFrame createFrame(JFrame parent);
}
