package Views.Styles.Default;

import Core.Game.GameAlike;
import Views.Styles.Drawer;
import Views.Styles.MultiplayerGameStyle;
import Views.Utils.ColorUtils;
import Views.Utils.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Владимир on 21.11.2016.
 */
public class DefaultMultiplayerStyle extends DefaultStyle implements MultiplayerGameStyle
{
    public final ArrayList<BufferedImage> snakesHeads = new ArrayList<>();
    public final ArrayList<BufferedImage> snakesCells = new ArrayList<>();
    public final ArrayList<Color> snakesColors = new ArrayList<>();
    public final int snakeColorAlpha = 50;

    public DefaultMultiplayerStyle() throws IOException
    {
        super();
        snakesColors.add(new Color(150, 255, 255));
        snakesColors.add(new Color(255, 0, 0));
        for(Color color : snakesColors)
        {
            snakesHeads.add(ImageUtils.getDyed(snakeHeadImage, ColorUtils.GetWithSpecifiedTransparency(color, snakeColorAlpha)));
            snakesCells.add(ImageUtils.getDyed(snakeCellImage, ColorUtils.GetWithSpecifiedTransparency(color, snakeColorAlpha)));
        }
    }

    @Override
    public Drawer CreateDrawer(Graphics2D g2d, GameAlike game, double turnPartLeft)
    {
        return new DefaultMultiplayerDrawer(this, g2d, game, turnPartLeft);
    }

    @Override
    public ArrayList<Color> getSnakesColors()
    {
        return snakesColors;
    }
}
