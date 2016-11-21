package Views.FinalScoreView;

import Views.Utils.MenuLabel;

/**
 * Created by ISmir on 21.11.2016.
 */
public class SinglePlayerFinalScoreFrame extends Frame
{
    public SinglePlayerFinalScoreFrame(Views.MainMenuView.Frame mainMenuFrame, Runnable restart, int finalScore)
    {
        super(mainMenuFrame, restart, new MenuLabel("Final Score: " + finalScore, 40));
    }
}
