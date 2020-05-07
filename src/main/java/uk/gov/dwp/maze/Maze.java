package uk.gov.dwp.maze;

import java.util.Arrays;

public class Maze {

    public static final int ENTRY = 2;
    public static final int EXIT = 3;
    public static final int WALL = 1;
    public static final int SPACE = 0;

    //Assuming maze is of rectangular size and all values are either filled with 0,1,2 or 3
    //0 for space ; 1 for wall ; 2 for entry point; 3 for exit
    final int maze[][];

    Maze(final int maze[][]) {
        validateLength(maze);
        validateNumberOfEntryPoint(maze);
        validateNumberOfExitPoint(maze);
        this.maze = maze;
    }

    public int[][] getMaze() {
        return maze;
    }

    int getValueAt(int x, int y) {
        int height = maze.length;
        int width = maze[0].length;
        if (x < 0 || x > width - 1 || y < 0 || y > height - 1) {
            throw new RuntimeException("Value out of boundaries. x should be -1 > x > " + width + " and y should be -1 > y > " + height);
        }
        return maze[x][y];
    }

    int getNumberOfWalls() {
        return (int) Arrays.stream(maze)
                .flatMapToInt(val -> Arrays.stream(val))
                .filter(val -> val == WALL)
                .count();
    }

    int getNumberOfEmptySpaces() {
        return (int) Arrays.stream(maze)
                .flatMapToInt(val -> Arrays.stream(val))
                .filter(val -> val == SPACE)
                .count();
    }

    private void validateLength(final int[][] maze) {
        if (maze.length == 0) {
            //toDo: custom exceptions
            throw new RuntimeException("Length of maze should be greater than zero");
        }
    }

    private void validateNumberOfEntryPoint(final int[][] maze) {
        long noOfEntryPoints = Arrays.stream(maze)
                .flatMapToInt(val -> Arrays.stream(val))
                .filter(val -> val == ENTRY)
                .count();
        if (noOfEntryPoints == 0 || noOfEntryPoints > 1) {
            throw new RuntimeException("the number of entry points is incorrect :" + noOfEntryPoints);
        }
    }

    private void validateNumberOfExitPoint(final int[][] maze) {
        long noOfExitPoints = Arrays.stream(maze)
                .flatMapToInt(val -> Arrays.stream(val))
                .filter(val -> val == EXIT)
                .count();
        if (noOfExitPoints == 0 || noOfExitPoints > 1) {
            throw new RuntimeException("the number of exit points is incorrect :" + noOfExitPoints);
        }
    }
}
