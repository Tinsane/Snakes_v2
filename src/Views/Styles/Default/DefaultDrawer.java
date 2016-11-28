package Views.Styles.Default;

import Core.Game.GameAlike;
import Core.GameObjects.CatDog;
import Core.GameObjects.Snake;
import Core.MapObjects.DynamicMapObjects.CatDogCell;
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
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by ISmir on 08.10.2016.
 */
public class DefaultDrawer implements MapObjectVisitor, Drawer
{
    private double x, y;
    protected final GameAlike game;
    private final double turnPartLeft;
    private final Graphics2D graphics;
    protected final DefaultStyle style;
    protected ArrayList<VisualItem> visualItems;

    public DefaultDrawer(DefaultStyle style, Graphics2D graphics, GameAlike game, double turnPartLeft)
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
    public void visit(EmptyCell emptyCell)
    {
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

    protected void addSnakeCell(SnakeCell snakeCell, BufferedImage headImage, BufferedImage cellImage)
    {
        boolean isHead = game
                .getGameObjects()
                .stream()
                .anyMatch(gameObject -> Snake.getSnakeOwner(game, snakeCell).head == snakeCell);
        BufferedImage image = isHead ? headImage : cellImage;
        BufferedImage rotatedImage = getRotated(image, VelocityVector.up.getAngle(snakeCell.getVelocity()));
        visualItems.add(new VisualItem(rotatedImage, x, y, 2));
    }

    @Override
    public void visit(SnakeCell snakeCell)
    {
        addSnakeCell(snakeCell, style.snakeHeadImage, style.snakeCellImage);
    }

    @Override
    public void visit(CatDogCell catDogCell)
    {
        addCatDogCell(catDogCell, style.catImage, style.dogImage, style.catDogBodyImage, style.catDogLegsImage);
    }

    private void addCatDogCell(CatDogCell cell, BufferedImage catImage, BufferedImage dogImage,
                               BufferedImage bodyImage, BufferedImage legsImage)
    {
        CatDog owner = CatDog.getCatDogOwner(game, cell);
        BufferedImage image;
        if (owner.head == cell)
            image = catImage;
        else if(owner.tail == cell)
            image = dogImage;
        else if (owner.head.previous == cell || cell.previous == owner.tail)
            image = legsImage;
        else
            image = bodyImage;
        BufferedImage rotatedImage = getRotated(image, VelocityVector.up.getAngle(cell.getVelocity()));
        visualItems.add(new VisualItem(rotatedImage, x, y, 2));
    }

    private void draw(MapObject mapObject, int x, int y)
    {
        this.x = x + mapObject.getVelocity().x * turnPartLeft;
        this.y = y + mapObject.getVelocity().y * turnPartLeft;
        mapObject.acceptVisitor(this);
    }

    public void draw()
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
