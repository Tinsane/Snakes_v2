package MainPackage;

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
            new Views.MainMenuView.Frame();
        }
        catch (IOException e)
        {

        }
    }
}
