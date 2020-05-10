package uk.gov.dwp.explorer;

import uk.gov.dwp.common.Coordinate;
import uk.gov.dwp.common.DIRECTION;
import uk.gov.dwp.common.Location;
import uk.gov.dwp.maze.Maze;

import java.util.HashMap;
import java.util.Map;

public class ExplorerHelper {

    public static Explorer getExplorerWithNewEntryLocation(Maze maze, Location newEntry) {

        final Coordinate entryPoint = maze.getEntryPoint();
        changeStateOfSpecificCoordinate(maze.getMaze(), entryPoint.getX(), entryPoint.getY(), Maze.SPACE);
        changeStateOfSpecificCoordinate(maze.getMaze(), newEntry.getCoordinate().getX(), newEntry.getCoordinate().getY(), Maze.ENTRY);
        final Explorer explorer = new Explorer(maze);
        changeExplorerDirection(explorer, explorer.getCurrentLocation().getDirection(), newEntry.getDirection());
        return explorer;
    }

    public static int[][] changeStateOfSpecificCoordinate(int[][] maze, int x, int y, int newState) {
        maze[y][x] = newState;
        return maze;
    }

    public static void printMaze(int[][] maze) {
        for (int i = maze.length - 1; i >= 0; i--) {
            for (int j = 0; j < maze[0].length; j++) {
                System.out.printf(maze[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void changeExplorerDirection(Explorer explorer, DIRECTION currentDirection, DIRECTION newDirection) {
        if (!currentDirection.equals(newDirection)) {
            final int numberOfleftTurnRequired = getnumOfLeftTurnStorage().get(currentDirection).get(newDirection);
            for (int i = 0; i < numberOfleftTurnRequired; i++) {
                explorer.turnLeft();
            }
        }
    }

    private static Map<DIRECTION, Map<DIRECTION, Integer>> getnumOfLeftTurnStorage() {

        Map<DIRECTION, Map<DIRECTION, Integer>> numOfLeftTurnStorage = new HashMap<>();
        //NORTH
        Map<DIRECTION, Integer> numOfLeftTurnStoragePerDirection = new HashMap<>();
        numOfLeftTurnStoragePerDirection.put(DIRECTION.W, 1);
        numOfLeftTurnStoragePerDirection.put(DIRECTION.S, 2);
        numOfLeftTurnStoragePerDirection.put(DIRECTION.E, 3);
        numOfLeftTurnStorage.put(DIRECTION.N, numOfLeftTurnStoragePerDirection);

        //SOUTH
        numOfLeftTurnStoragePerDirection = new HashMap<>();
        numOfLeftTurnStoragePerDirection.put(DIRECTION.E, 1);
        numOfLeftTurnStoragePerDirection.put(DIRECTION.N, 2);
        numOfLeftTurnStoragePerDirection.put(DIRECTION.W, 3);
        numOfLeftTurnStorage.put(DIRECTION.S, numOfLeftTurnStoragePerDirection);

        //EAST
        numOfLeftTurnStoragePerDirection = new HashMap<>();
        numOfLeftTurnStoragePerDirection.put(DIRECTION.N, 1);
        numOfLeftTurnStoragePerDirection.put(DIRECTION.W, 2);
        numOfLeftTurnStoragePerDirection.put(DIRECTION.S, 3);
        numOfLeftTurnStorage.put(DIRECTION.E, numOfLeftTurnStoragePerDirection);

        //WEST
        numOfLeftTurnStoragePerDirection = new HashMap<>();
        numOfLeftTurnStoragePerDirection.put(DIRECTION.S, 1);
        numOfLeftTurnStoragePerDirection.put(DIRECTION.E, 2);
        numOfLeftTurnStoragePerDirection.put(DIRECTION.N, 3);
        numOfLeftTurnStorage.put(DIRECTION.W, numOfLeftTurnStoragePerDirection);

        return numOfLeftTurnStorage;
    }
}
