package Views.FinalScoreView;

import Views.Utils.ColoredMenuLabel;

/**
 * Created by ISmir on 21.11.2016.
 */
public class MultiplayerFinalScoreFrame extends Frame
{
    public MultiplayerFinalScoreFrame(Views.MainMenuView.Frame mainMenuFrame, Runnable restart, int winnerIndex)
    {
        super(mainMenuFrame, restart, new ColoredMenuLabel(String.format("The winner is Player %d!", winnerIndex + 1), 40,
                mainMenuFrame.settings.multiplayerStyle.getSnakesColors().get(winnerIndex)));
    }
}
