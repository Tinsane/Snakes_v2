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
    private final CatDog catDog;
    private LinkedList<IntPair> path = new LinkedList<>();
    private boolean madness = false;
    public CatDogAI(CatDog catDog)
    {
        super();
        this.catDog = catDog;
    }

    private boolean isPassable(MapObject mapObject)
    {
        return mapObject instanceof EmptyCell || mapObject instanceof Berry;
    }

    private boolean checkPath(Game game)
    {
        for(IntPair cellPosition : path)
            if (!isPassable(game.getCellAt(cellPosition)))
                return false;
        return !path.isEmpty();
    }

    private VelocityVector getRandomMovementDirection(IntPair position, Game game, boolean[][] visited)
    {
        Random random = new Random();
        ArrayList<VelocityVector> validDirections = new ArrayList<>();
        for(VelocityVector direction : VelocityVector.directions)
        {
            IntPair newPosition = position.getAdded(direction.getIntPair());
            if ((visited == null || !visited[newPosition.x][newPosition.y]) && isPassable(game.getCellAt(newPosition)))
                validDirections.add(direction);
        }
        if (validDirections.isEmpty())
            return null;
        return validDirections.get(random.nextInt(validDirections.size()));
    }

    private void makeMadMove(Game game)
    {
        game.executeCommand(new ChangeCatDogRulerCommand(catDog));
        VelocityVector randomDirection = getRandomMovementDirection(catDog.getRulerCoordinates(game.getCurrentMap()), game, null);
        if (randomDirection == null)
            randomDirection = VelocityVector.zero;
        game.executeCommand(new ChangeCatDogVelocityCommand(catDog, randomDirection));
    }

    @Override
    public void Order(Game game)
    {
        Random random = new Random();
        if (random.nextDouble() < MADNESS_TRIGGER_CHANCE)
        {
            madness = !madness;
            path.clear();
        }
        if (madness)
        {
            makeMadMove(game);
            return;
        }
        if (!checkPath(game))
            path = findFoodPath(game);
        if (path.size() == 0)
        {
            game.executeCommand(new ChangeCatDogVelocityCommand(catDog, VelocityVector.zero));
            return;
        }
        IntPair rulerCoordinates = catDog.getRulerCoordinates(game.getCurrentMap());
        for(VelocityVector vector : VelocityVector.directions)
            if (path.get(0).equals(rulerCoordinates.getAdded(vector.getIntPair())))
                game.executeCommand(new ChangeCatDogVelocityCommand(catDog, vector));
        path.removeFirst();
    }

    private LinkedList<IntPair> findFoodPath(Game game)
    {
        LinkedList<IntPair> path = new LinkedList<>();
        path.add(catDog.getRulerCoordinates(game.getCurrentMap()));
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
            VelocityVector move = getRandomMovementDirection(currentPosition, game, visited);
            if (move == null)
                path.removeLast();
            else
                path.add(currentPosition.getAdded(move.getIntPair()));
        }
        return path;
    }
}
