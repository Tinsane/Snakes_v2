package Views.FinalScoreView;

import Views.GameView.Settings;
import Views.Styles.MenuStyle;
import Views.Utils.ColoredMenuLabel;

import java.awt.*;

/**
 * Created by ISmir on 21.11.2016.
 */
public class MultiplayerFinalScoreFrame extends Frame
{
    public MultiplayerFinalScoreFrame(Views.MainMenuView.Frame mainMenuFrame, Runnable restart, int winnerIndex)
    {
        super(mainMenuFrame, restart, getLabelByWinnerIndex(winnerIndex, mainMenuFrame.settings));
    }

    private static ColoredMenuLabel getLabelByWinnerIndex(int winnerIndex, Settings settings)
    {
        if (winnerIndex != -1)
            return new ColoredMenuLabel(String.format("The winner is Player %d!", winnerIndex + 1), 40,
                    settings.multiplayerStyle.getSnakesColors().get(winnerIndex));
        else
            return new ColoredMenuLabel("Draw!", 40, MenuStyle.TEXT_COLOR);
    }
}
