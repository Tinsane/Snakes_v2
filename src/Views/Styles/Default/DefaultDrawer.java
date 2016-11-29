package Views.Styles.Default;

import Core.Game.GameAlike;
import Core.GameObjects.CatDog;
import Core.GameObjects.GameObject;
import Core.GameObjects.GameObjectVisitor;
import Core.GameObjects.Snake;
import Core.MapObjects.DynamicMapObjects.BigObjectCell;
import Core.MapObjects.DynamicMapObjects.CatDogCell;
import Core.MapObjects.DynamicMapObjects.SnakeCell;
import Core.MapObjects.MapObject;
import Core.MapObjects.StaticMapObjects.Berries.Blueberry;
import Core.MapObjects.StaticMapObjects.Berries.Strawberry;
import Core.MapObjects.StaticMapObjects.EmptyCell;
import Core.MapObjects.StaticMapObjects.SandGlass;
import Core.MapObjects.StaticMapObjects.Wall;
import Core.Utils.IntPair;
import Core.Utils.VelocityVector;
import Core.MapObjects.MapObjectVisitor;
import Views.Styles.Drawer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by ISmir on 08.10.2016.
 */
public class DefaultDrawer implements MapObjectVisitor, GameObjectVisitor, Drawer
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

    @Override
    public void visit(SnakeCell snakeCell)
    {
        throw new NotImplementedException();
    }

    @Override
    public void visit(CatDogCell catDogCell)
    {
        throw new NotImplementedException();
    }

    protected void addRotatedImage(BufferedImage image, VelocityVector direction, int drawingPriority)
    {
        BufferedImage rotatedImage = getRotated(image, VelocityVector.up.getAngle(direction));
        visualItems.add(new VisualItem(rotatedImage, x, y, drawingPriority));
    }

    private void setCoordinates(MapObject mapObject, int x, int y)
    {
        this.x = x + mapObject.getVelocity().x * turnPartLeft;
        this.y = y + mapObject.getVelocity().y * turnPartLeft;
    }

    private void draw(MapObject mapObject, int x, int y)
    {
        setCoordinates(mapObject, x, y);
        mapObject.acceptVisitor(this);
    }

    public void draw()
    {
        MapObject[][] map = game.getCurrentMap();
        for (int i = 0; i < map.length; ++i)
            for (int j = 0; j < map[0].length; ++j)
            {
                drawImage(style.emptyCellImage, i, j);
                if (game.getOwner(map[i][j]) == null)
                    draw(map[i][j], i, j);
            }
        for(GameObject gameObject : game.getGameObjects())
            gameObject.acceptVisitor(this);
        visualItems.sort((a, b) -> a.priority.compareTo(b.priority));
        for (VisualItem item : visualItems)
            drawImage(item.image, item.x, item.y);
    }

    private void addCatDogImage(CatDog owner, CatDogCell cell, VelocityVector direction)
    {
        BufferedImage image;
        if (cell == owner.getCat())
            image = style.catImage;
        else if(cell == owner.getDog())
            image = style.dogImage;
        else
            image = style.catDogBodyImage;
        addRotatedImage(image, direction, 2);
    }

    @Override
    public void visit(CatDog catDog)
    {
        IntPair coordinates = catDog.head.getCoordinates(game.getCurrentMap());
        VelocityVector direction = catDog.head.getDirectionToPrevious(game.getCurrentMap(), coordinates).getReversed();
        for(BigObjectCell cell : catDog)
        {
            if (coordinates == null)
                break;
            setCoordinates(cell, coordinates.x, coordinates.y);
            addCatDogImage(catDog, (CatDogCell) cell, direction);
            direction = cell.getDirectionToPrevious(game.getCurrentMap(), coordinates);
            if (cell.previous != null)
                coordinates = cell.getPreviousCoordinates(game.getCurrentMap(), coordinates);
        }
    }

    protected void addSnakeCellImage(Snake owner, SnakeCell cell)
    {
        addRotatedImage(cell == owner.head ? style.snakeHeadImage : style.snakeCellImage, cell.getVelocity(), 2);
    }

    @Override
    public void visit(Snake snake)
    {
        IntPair coordinates = snake.head.getCoordinates(game.getCurrentMap());
        for(BigObjectCell cell : snake)
        {
            if (coordinates == null)
                break;
            setCoordinates(cell, coordinates.x, coordinates.y);
            addSnakeCellImage(snake, (SnakeCell) cell);
            if (cell.previous != null)
                coordinates = cell.getPreviousCoordinates(game.getCurrentMap(), coordinates);
        }
    }
}
