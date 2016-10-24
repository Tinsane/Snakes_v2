package Views.FinalScoreView;

import Views.MainMenuView.MainMenuRestorer;

import javax.swing.*;

/**
 * Created by Владимир on 24.10.2016.
 */
public class Frame extends JFrame
{
    public Frame(Views.MainMenuView.Frame mainMenuFrame, int finalScore)
    {
        super();
        addWindowListener(new MainMenuRestorer(this, mainMenuFrame));
        add(new Canvas(finalScore));
        setSize(100, 200);
        setVisible(true);
    }
}
