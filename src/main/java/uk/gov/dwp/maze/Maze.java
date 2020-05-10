package uk.gov.dwp.maze;

import uk.gov.dwp.common.Coordinate;

import java.util.Arrays;

public class Maze {

    public static final int SPACE = 0;
    public static final int WALL = 1;
    public static final int ENTRY = 2;
    public static final int EXIT = 3;

    final int maze[][];

    public Maze(final int maze[][]) {
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
            throw new InvalidMazeException("Value out of boundaries. x should be -1 > x > " + width + " and y should be -1 > y > " + height);
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

    public Coordinate getEntryPoint() {
        int x = 0;
        int y = 0;
        out:
        for (int xAxis = 0; xAxis < maze[0].length; xAxis++) {
            for (int yAxis = 0; yAxis < maze.length; yAxis++) {
                if (maze[yAxis][xAxis] == ENTRY) {
                    x = xAxis;
                    y = yAxis;
                    break out;
                }
            }
        }
        return new Coordinate(x, y);
    }

    private void validateLength(final int[][] maze) {
        if (maze.length == 0) {
            throw new InvalidMazeException("Length of maze should be greater than zero");
        }
    }

    private void validateNumberOfEntryPoint(final int[][] maze) {
        long noOfEntryPoints = Arrays.stream(maze)
                .flatMapToInt(val -> Arrays.stream(val))
                .filter(val -> val == ENTRY)
                .count();
        if (noOfEntryPoints == 0 || noOfEntryPoints > 1) {
            throw new InvalidMazeException("the number of entry points is incorrect :" + noOfEntryPoints);
        }
    }

    private void validateNumberOfExitPoint(final int[][] maze) {
        long noOfExitPoints = Arrays.stream(maze)
                .flatMapToInt(val -> Arrays.stream(val))
                .filter(val -> val == EXIT)
                .count();
        if (noOfExitPoints == 0 || noOfExitPoints > 1) {
            throw new InvalidMazeException("the number of exit points is incorrect :" + noOfExitPoints);
        }
    }
}
