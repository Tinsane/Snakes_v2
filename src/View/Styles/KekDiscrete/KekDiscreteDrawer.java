package View.Styles.KekDiscrete;

import Core.Game.Game;
import Core.MapObjects.DynamicMapObjects.SnakeCell;
import Core.MapObjects.MapObject;
import Core.MapObjects.StaticMapObjects.Berries.Blueberry;
import Core.MapObjects.StaticMapObjects.Berries.Strawberry;
import Core.MapObjects.StaticMapObjects.SandGlass;
import Core.MapObjects.StaticMapObjects.Wall;
import Core.Utils.VelocityVector;
import Core.MapObjects.MapObjectVisitor;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by ISmir on 08.10.2016.
 */
public class KekDiscreteDrawer implements MapObjectVisitor
{
    private int x, y;
    private Game game;
    private Graphics2D graphics;
    private KekDiscreteStyle style;
    private final Random random;
    private final double angles[] = new double[] {0, Math.PI / 2, Math.PI, -Math.PI / 2};

    public KekDiscreteDrawer(KekDiscreteStyle style, Graphics2D graphics, Game game, double turnPartLeft)
    {
        this.game = game;
        this.graphics = graphics;
        this.style = style;
        random = new Random();
    }

    private void drawImage(BufferedImage image)
    {
        graphics.drawImage(image, x * style.getTileSize(), y * style.getTileSize(), null);
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
        if (snakeCell == game.snake.head)
            drawImage(getRotated(style.snakeHeadImage,
                    VelocityVector.up.getAngle(snakeCell.getVelocity())));
        else
            drawImage(getRotated(getRotated(style.snakeHeadImage, angles[random.nextInt(angles.length)]),
                    VelocityVector.up.getAngle(snakeCell.getVelocity())));
    }

    public void draw(MapObject mapObject, int x, int y)
    {
        this.x = x;
        this.y = y;
        drawImage(style.emptyCellImage);
        mapObject.acceptVisitor(this);
    }

    public void visitAll()
    {
        MapObject[][] map = game.getCurrentMap();
        for (int i = 0; i < map.length; ++i)
            for (int j = 0; j < map[0].length; ++j)
                draw(map[i][j], i, j);
    }
}
