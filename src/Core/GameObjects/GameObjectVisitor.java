package Core.GameObjects;

/**
 * Created by Владимир on 29.11.2016.
 */
public interface GameObjectVisitor
{
    void visit(CatDog catDog);

    void visit(Snake snake);
}
