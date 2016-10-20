package View.Styles.Default;

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
import java.util.ArrayList;

/**
 * Created by ISmir on 08.10.2016.
 */
public class DefaultDrawer implements MapObjectVisitor
{
    private double x, y;
    private Game game;
    private double turnPartLeft;
    private Graphics2D graphics;
    private DefaultStyle style;
    private ArrayList<VisualItem> visualItems;

    public DefaultDrawer(DefaultStyle style, Graphics2D graphics, Game game, double turnPartLeft)
    {
        this.game = game;
        this.turnPartLeft = turnPartLeft;
        this.graphics = graphics;
        this.style = style;
        visualItems = new ArrayList<>();
    }

    private void drawImage(BufferedImage image, double x, double y)
    {
        graphics.drawImage(image, (int)Math.round(x * style.getTileSize()), (int)Math.round(y * style.getTileSize()), null);
    }

    @Override
    public void visit(Wall wall)
    {
        visualItems.add(new VisualItem(style.wallImage, x, y, 3));
    }

    @Override
    public void visit(SandGlass sandGlass)
    {
        visualItems.add(new VisualItem(style.sandGlassImage, x, y, 1));
    }

    @Override
    public void visit(Strawberry strawberry)
    {
        visualItems.add(new VisualItem(style.strawberryImage, x, y, 1));
    }

    @Override
    public void visit(Blueberry blueberry)
    {
        visualItems.add(new VisualItem(style.blueberryImage, x, y, 1));
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
        visualItems.add(new VisualItem(getRotated(snakeCell == game.snake.head ? style.snakeHeadImage : style.snakeCellImage,
                VelocityVector.up.getAngle(snakeCell.getVelocity())), x, y, 2));
//        if (snakeCell != game.snake.head)
//            visualItems.add(new VisualItem(style.snakeSquareImage, (int)x, (int)y, 2));
    }

    private void draw(MapObject mapObject, int x, int y)
    {
        this.x = x + mapObject.getVelocity().x * turnPartLeft;
        this.y = y + mapObject.getVelocity().y * turnPartLeft;
        mapObject.acceptVisitor(this);
    }

    public void visitAll()
    {
        MapObject[][] map = game.getCurrentMap();
        for (int i = 0; i < map.length; ++i)
            for (int j = 0; j < map[0].length; ++j)
            {
                drawImage(style.emptyCellImage, i, j);
                draw(map[i][j], i, j);
            }
        visualItems.sort((a, b) -> a.priority.compareTo(b.priority));
        for (VisualItem item : visualItems)
            drawImage(item.image, item.x, item.y);
    }
}
