package Core.GameObjects;

import Core.Game.GameAlike;
import Core.GameUpdatingSystem.GameUpdaters.GameMovementUpdater;
import Core.MapObjects.DynamicMapObjects.BigObjectCell;
import Core.MapObjects.DynamicMapObjects.CatDogCell;
import Core.MapObjects.MapObject;
import Core.Utils.IntPair;
import Core.Utils.VelocityVector;

/**
 * Created by Владимир on 27.11.2016.
 */

public class CatDog extends BigMapObject
{
    public boolean tailRules;
    private int extension;
    public CatDog(int length) { this(length, new CatDogCell()); }

    public CatDog(int length, CatDogCell head)
    {
        super(1, head);
        extension = length - 1;
        tailRules = false;
        extendNonRuler();
    }

    public CatDogCell getCat()
    {
        return (CatDogCell) head;
    }

    public CatDogCell getDog()
    {
        return (CatDogCell) tail;
    }

    public CatDogCell getRuler()
    {
        return (CatDogCell) (tailRules ? tail : head);
    }

    private void moveFromTail(GameMovementUpdater updater, BigObjectCell cell, IntPair cellPosition)
    {
        if (cell.previous != null)
        {
            IntPair previousPosition = cellPosition == null ? cell.previous.getCoordinates(updater.getCurrentMap()) :
                    cell.getPreviousCoordinates(updater.getCurrentMap(), cellPosition);
            VelocityVector previousVelocity = cell.previous.getVelocity();
            moveFromTail(updater, cell.previous, previousPosition);
            if (cellPosition == null)
                updater.placeObject(cell, previousPosition);
            else
                updater.moveObject(cellPosition);
            cell.setVelocity(previousVelocity);
            return;
        }
        updater.moveObject(cellPosition);
    }

    @Override
    public void updatePosition(GameMovementUpdater updater)
    {
        if (!getRuler().getVelocity().equals(VelocityVector.zero) && extension != 0)
        {
            extendNonRuler();
            if (tailRules)
            {
                moveFromTail(updater, head, null);
                return;
            }
        }
        if (tailRules)
            moveFromTail(updater, head, head.getCoordinates(updater.getCurrentMap()));
        else
            super.updatePosition(updater);
    }

    @Override
    public void acceptVisitor(GameObjectVisitor visitor)
    {
        visitor.visit(this);
    }

    private void extendNonRuler()
    {
        if (extension <= 0)
            throw new UnsupportedOperationException("Cannot extend with non positive extension.");
        --extension;
        if (!tailRules)
        {
            CatDogCell newTail = new CatDogCell();
            tail.previous = newTail;
            tail = newTail;
        }
        else
            head = new CatDogCell((CatDogCell) head);
    }

    @Override
    public int getLength()
    {
        return super.getLength() + extension;
    }

    public void setVelocity(VelocityVector vector, MapObject[][] map)
    {
        if (getRuler().getVelocity().equals(vector))
            return;
        if (vector.equals(VelocityVector.zero))
        {
            for(BigObjectCell bigObjectCell : this)
                bigObjectCell.setVelocity(vector);
            return;
        }
        if (getRuler().getVelocity().equals(VelocityVector.zero))
            setCellVelocity(head, map, head.getCoordinates(map));
        getRuler().setVelocity(vector);
    }

    private void setCellVelocity(BigObjectCell cell, MapObject[][] map, IntPair cellPosition)
    {
        if (cell.previous == null)
            return;
        IntPair previousPosition = cell.getPreviousCoordinates(map, cellPosition);
        setCellVelocity(cell.previous, map, previousPosition);
        VelocityVector vector = cell.getDirectionToPrevious(map, cellPosition);
        if (tailRules)
            cell.setVelocity(vector);
        else
            cell.previous.setVelocity(vector.getReversed());
    }

    @Override
    public void incLength()
    {
        ++extension;
    }

    public static CatDog getCatDogOwner(GameAlike game, CatDogCell cell)
    {
        return (CatDog) game.getOwner(cell);
    }

    public IntPair getRulerCoordinates(MapObject[][] map)
    {
        return getRuler().getCoordinates(map);
    }
}
