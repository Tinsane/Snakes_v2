package Views.FinalScoreView;

import Views.Utils.MenuLabel;

/**
 * Created by ISmir on 21.11.2016.
 */
public class MultiplayerFinalScoreFrame extends Frame
{
    public MultiplayerFinalScoreFrame(Views.MainMenuView.Frame mainMenuFrame, Runnable restart, String winnerName)
    {
        super(mainMenuFrame, restart, new MenuLabel(String.format("The winner is %s!", winnerName), 40));
    }
}
