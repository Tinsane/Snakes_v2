package View;

import java.io.IOException;

/**
 * Created by ISmir on 08.10.2016.
 */
public class Main
{
    public static void main(String[] args)
    {
        try
        {
            GameView view = new GameView();
            view.start();
            view.setVisible(true);
        }
        catch (IOException e)
        {
            // TODO: logging
        }
    }
}
