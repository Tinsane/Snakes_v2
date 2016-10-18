package View;

import Core.Game.Game;
import Core.MapObjects.MapObject;
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
        Graphics2D g2d = (Graphics2D)g;
        MapObject[][] map = game.getCurrentMap();
        for (int i = 0; i < map.length; ++i)
            for(int j = 0; j < map[0].length; ++j)
            {
                int finalI = i;
                int finalJ = j;
                map[i][j].draw(style, game, (Image image) ->
                        g2d.drawImage(image, finalI * style.getTileSize(), finalJ * style.getTileSize(), null));
            }
    }
}
