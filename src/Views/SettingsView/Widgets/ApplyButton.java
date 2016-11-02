package Views.SettingsView.Widgets;

import Views.Utils.CloseFrameButton;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Владимир on 02.11.2016.
 */
public final class ApplyButton extends CloseFrameButton
{
    private final Views.MainMenuView.Frame mainMenuFrame;
    public ApplyButton(int fontSize, JFrame frame, Views.MainMenuView.Frame mainMenuFrame)
    {
        super("Apply", fontSize, frame);
        this.mainMenuFrame = mainMenuFrame;
    }

    @Override
    protected void onClose(ActionEvent e)
    {
        mainMenuFrame.settings = ((Views.SettingsView.Frame) frame).settings;
        super.onClose(e);
    }
}
