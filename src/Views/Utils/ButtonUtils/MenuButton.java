package Views.Utils.ButtonUtils;

import Views.Styles.MenuStyle;

/**
 * Created by Владимир on 31.10.2016.
 */
public class MenuButton extends TextButton
{
    public MenuButton(String text, int fontSize)
    {
        super(text, MenuStyle.getFont(fontSize), MenuStyle.TEXT_COLOR, MenuStyle.PRESSED_BUTTON_COLOR);
    }
}
