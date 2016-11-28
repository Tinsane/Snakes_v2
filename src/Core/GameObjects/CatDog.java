package Core.GameObjects;

import Core.Game.GameAlike;
import Core.GameUpdatingSystem.GameUpdaters.GameMovementUpdater;
import Core.MapObjects.DynamicMapObjects.BigObjectCell;
import Core.MapObjects.DynamicMapObjects.CatDogCell;
import Core.Utils.IntPair;
import Core.Utils.VelocityVector;
import sun.plugin.dom.exception.InvalidStateException;

/**
 * Created by Владимир on 27.11.2016.
 */

// TODO for Vova: create properties for cat and dog.
public class CatDog extends BigMapObject
{
    public boolean tailRules = false;
    private int extension = 0;
    public CatDog(int length) { this(length, new CatDogCell()); }

    public CatDog(int length, CatDogCell head)
    {
        super(length, head);
    }

    private void moveFromTail(GameMovementUpdater updater, BigObjectCell cell, IntPair cellPosition)
    {
        if (cell.previous != null)
        {
            IntPair previousPosition = cellPosition == null ? cell.previous.getCoordinates(updater.getCurrentMap()) :
                    cell.getPreviousCoordinates(updater.getCurrentMap(), cellPosition);
            moveFromTail(updater, cell.previous, previousPosition);
            if (cellPosition == null)
            {
                cellPosition = previousPosition;
                setVelocity(VelocityVector.zero);
            }
            else
                setVelocity(cell.previous.getVelocity());
        }
        updater.moveObject(cellPosition);
    }

    private void extendTail() // TODO for Vova: create function create ruler
    {
        if (extension <= 0)
            throw new InvalidStateException("Cannot extend with non positive extension.");
        CatDogCell newTail = new CatDogCell();
        tail.previous = newTail;
        tail = newTail; // TODO for Vova: create function setRuler
        --extension;
    }

    private void extendHead()// TODO for Vova: create function create ruler
    {
        if (extension <= 0)
            throw new InvalidStateException("Cannot extend with non positive extension.");
        head = new CatDogCell((CatDogCell) head);
        --extension;
    }

    @Override
    public void updatePosition(GameMovementUpdater updater)
    {
        if (!tailRules) // TODO for Vova: here try to do without if
        {
            if (head.getVelocity() != VelocityVector.zero && extension != 0)
                extendTail();
            super.updatePosition(updater);
        }
        else
        {
            if (tail.getVelocity() != VelocityVector.zero && extension != 0)
                extendHead();
            moveFromTail(updater, head, null);
        }
    }

    @Override
    public int getLength()
    {
        return super.getLength() + extension;
    }

    public void setVelocity(VelocityVector vector)
    {
        if (tailRules) // TODO for Vova: the same, getRuler.setVelocity(vector);
            tail.setVelocity(vector);
        else
            head.setVelocity(vector);
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
}
