package Views.Styles.DefaultDiscrete;

import Core.Game.GameAlike;
import Core.MapObjects.DynamicMapObjects.SnakeCell;
import Core.MapObjects.MapObject;
import Core.MapObjects.StaticMapObjects.Berries.Blueberry;
import Core.MapObjects.StaticMapObjects.Berries.Strawberry;
import Core.MapObjects.StaticMapObjects.EmptyCell;
import Core.MapObjects.StaticMapObjects.SandGlass;
import Core.MapObjects.StaticMapObjects.Wall;
import Core.Utils.VelocityVector;
import Core.MapObjects.MapObjectVisitor;
import Views.Styles.Drawer;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * Created by ISmir on 08.10.2016.
 */
public class DefaultDiscreteDrawer implements MapObjectVisitor, Drawer
{
    private int x, y;
    private final GameAlike game;
    private final Graphics2D graphics;
    private final DefaultDiscreteStyle style;

    public DefaultDiscreteDrawer(DefaultDiscreteStyle style, Graphics2D graphics, GameAlike game, double turnPartLeft)
    {
        this.game = game;
        this.graphics = graphics;
        this.style = style;
    }

    public void drawImage(BufferedImage image, int x, int y)
    {
        graphics.drawImage(image, x * style.getTileSize(), y * style.getTileSize(), null);
    }

    private void drawImage(BufferedImage image)
    {
        drawImage(image, x, y);
    }

    @Override
    public void visit(EmptyCell emptyCell)
    {
    }

    @Override
    public void visit(Wall wall)
    {
        drawImage(style.wallImage);
    }

    @Override
    public void visit(SandGlass sandGlass)
    {
        drawImage(style.sandGlassImage);
    }

    @Override
    public void visit(Strawberry strawberry)
    {
        drawImage(style.strawberryImage);
    }

    @Override
    public void visit(Blueberry blueberry)
    {
        drawImage(style.blueberryImage);
    }

    private BufferedImage getRotated(BufferedImage image, double angle)
    {
        double centerX = image.getWidth() / 2.0;
        double centerY = image.getHeight() / 2.0;
        return new AffineTransformOp(AffineTransform.getRotateInstance(angle, centerX, centerY),
                AffineTransformOp.TYPE_BILINEAR).filter(image, null);
    }

    @Override
    public void visit(SnakeCell snakeCell)
    {
        drawImage(getRotated(game.getGameObjects().stream().anyMatch(object -> object.contains(snakeCell)) ?
                        style.snakeHeadImage :
                        style.snakeCellImage,
                VelocityVector.up.getAngle(snakeCell.getVelocity())));
    }

    public void draw(MapObject mapObject, int x, int y)
    {
        this.x = x;
        this.y = y;
        drawImage(style.emptyCellImage);
        mapObject.acceptVisitor(this);
    }

    public void draw()
    {
        MapObject[][] map = game.getCurrentMap();
        for (int i = 0; i < map.length; ++i)
            for (int j = 0; j < map[0].length; ++j)
                draw(map[i][j], i, j);
    }
}
