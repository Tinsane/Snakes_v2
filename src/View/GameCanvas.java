package View;

import Core.Game.Game;
import Core.MapObjects.MapObject;
import View.Styles.GameDrawer;
import View.Styles.GameStyle;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Владимир on 11.10.2016.
 */
public class GameCanvas extends JPanel
{
    Game game;
    GameStyle style;

    GameCanvas(Game game, GameStyle style, boolean doubleBuffered)
    {
        super(doubleBuffered);
        this.game = game;
        this.style = style;
    }
    @Override
    public void paint(Graphics g)
    {

    }
}
