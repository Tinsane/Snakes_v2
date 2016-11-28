package AI;

import Core.Game.Game;
import Core.GameCommands.ChangeCatDogRulerCommand;
import Core.GameCommands.ChangeCatDogVelocityCommand;
import Core.GameObjects.CatDog;
import Core.MapObjects.MapObject;
import Core.MapObjects.StaticMapObjects.Berries.Berry;
import Core.MapObjects.StaticMapObjects.EmptyCell;
import Core.Utils.IntPair;
import Core.Utils.VelocityVector;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Владимир on 27.11.2016.
 */
public class CatDogAI extends BaseAI
{
    private static final double MADNESS_TRIGGER_CHANCE = 0.1;
    private final Random random; // TODO for Vova: create in function
    private final CatDog catDog;
    private LinkedList<IntPair> path = new LinkedList<>();
    private boolean madness = false;
    public CatDogAI(Game game, CatDog catDog)
    {
        super(game);
        this.catDog = catDog;
        random = new Random();
    }

    private boolean isPassable(MapObject mapObject)
    {
        return mapObject instanceof EmptyCell || mapObject instanceof Berry;
    }

    private boolean checkPath()
    {
        for(IntPair cellPosition : path)
            if (!isPassable(game.getCellAt(cellPosition)))
                return false;
        return !path.isEmpty();
    }

    private IntPair getRulerCoordinates() // TODO for Vova: move inside CatDog
    {
        return catDog.tailRules ?
                catDog.tail.getCoordinates(game.getCurrentMap()) :
                catDog.head.getCoordinates(game.getCurrentMap());
    }

    private VelocityVector getRandomMovementDirection(IntPair position)
    {
        ArrayList<VelocityVector> validDirections = new ArrayList<>();
        for(VelocityVector direction : VelocityVector.directions)
            if (isPassable(game.getCellAt(position.getAdded(direction.getIntPair()))))
                validDirections.add(direction);
        if (validDirections.isEmpty())
            return null;
        return validDirections.get(random.nextInt(validDirections.size()));
    }

    private void makeMadMove()
    {
        game.executeCommand(new ChangeCatDogRulerCommand(catDog));
        VelocityVector randomDirection = getRandomMovementDirection(getRulerCoordinates());
        if (randomDirection == null)
            randomDirection = VelocityVector.zero;
        game.executeCommand(new ChangeCatDogVelocityCommand(catDog, randomDirection));
    }

    @Override
    public void updateStrategy()
    {
        if (random.nextDouble() < MADNESS_TRIGGER_CHANCE)
        {
            madness = !madness;
            path.clear();
        }
        if (madness)
        {
            makeMadMove();
            return;
        }
        if (!checkPath())
            path = findFoodPath();
        if (path.size() == 0)
        {
            game.executeCommand(new ChangeCatDogVelocityCommand(catDog, VelocityVector.zero));
            return;
        }
        IntPair rulerCoordinates = getRulerCoordinates();
        for(VelocityVector vector : VelocityVector.directions)
            if (path.get(0).equals(rulerCoordinates.getAdded(vector.getIntPair())))
                game.executeCommand(new ChangeCatDogVelocityCommand(catDog, vector));
        path.removeFirst();
    }

    private LinkedList<IntPair> findFoodPath()
    {
        LinkedList<IntPair> path = new LinkedList<>();
        path.add(getRulerCoordinates());
        MapObject[][] map = game.getCurrentMap();
        boolean[][] visited = new boolean[map.length][map[0].length];
        while(!path.isEmpty())
        {
            IntPair currentPosition = path.getLast();
            visited[currentPosition.x][currentPosition.y] = true;
            if (game.getCellAt(currentPosition) instanceof Berry)
            {
                path.removeFirst();
                return path;
            }
            VelocityVector move = getRandomMovementDirection(currentPosition);
            if (move == null)
                path.removeLast();
            else
                path.add(currentPosition.getAdded(move.getIntPair()));
        }
        return path;
    }
}
