package uk.gov.dwp.maze;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MazeTest {

    @DisplayName("should return correct state of the maze at provided coordinates")
    @Test
    public void shouldReturnTheCorrectValueAtCoordinate() {
        int[][] newMaze = {{2, 0, 0}, {0, 0, 0}, {1, 3, 0}};
        Maze maze = new Maze(newMaze);
        assertEquals(maze.getMaze()[1][1], maze.getValueAt(1, 1));
    }

    @DisplayName("should throw exception if x coordinate is less than 0")
    @Test
    public void shouldThrowExceptionIfXcoordinateIsLessThanZero() {
        int[][] newMaze = {{2, 0, 0}, {0, 0, 0}, {1, 3, 0}};
        Maze maze = new Maze(newMaze);
        Exception exception = assertThrows(InvalidMazeException.class, () -> maze.getValueAt(-1, 1));
        assertEquals("Value out of boundaries. x should be -1 > x > 3 and y should be -1 > y > 3", exception.getMessage());
    }

    @DisplayName("should throw exception if x coordinate is greater than maze width")
    @Test
    public void shouldThrowExceptionIfXcoordinateIsGreaterThanMazeWidth() {
        int[][] newMaze = {{2, 0, 0}, {0, 0, 0}, {1, 3, 0}};
        Maze maze = new Maze(newMaze);
        Exception exception = assertThrows(InvalidMazeException.class, () -> maze.getValueAt(16, 1));
        assertEquals("Value out of boundaries. x should be -1 > x > 3 and y should be -1 > y > 3", exception.getMessage());
    }

    @DisplayName("should throw exception if y coordinate is less than 0")
    @Test
    public void shouldThrowExceptionIfYcoordinateIsLessThanZero() {
        int[][] newMaze = {{2, 0, 0}, {0, 0, 0}, {1, 3, 0}};
        Maze maze = new Maze(newMaze);
        Exception exception = assertThrows(InvalidMazeException.class, () -> maze.getValueAt(1, -2));
        assertEquals("Value out of boundaries. x should be -1 > x > 3 and y should be -1 > y > 3", exception.getMessage());
    }

    @DisplayName("should throw exception if y coordinate is greater than maze height")
    @Test
    public void shouldThrowExceptionIfYcoordinateIsGreaterThanMazeHeight() {
        int[][] newMaze = {{2, 0, 0}, {0, 0, 0}, {1, 3, 0}};
        Maze maze = new Maze(newMaze);
        Exception exception = assertThrows(InvalidMazeException.class, () -> maze.getValueAt(1, 3));
        assertEquals("Value out of boundaries. x should be -1 > x > 3 and y should be -1 > y > 3", exception.getMessage());
    }

    @DisplayName("should return correct number of empty spaces in maze")
    @Test
    public void shouldReturnTotalEmptySpacesInMaze() {
        int[][] newMaze = {{2, 0, 0}, {0, 0, 0}, {1, 3, 0}};
        Maze maze = new Maze(newMaze);
        assertEquals(6, maze.getNumberOfEmptySpaces());
    }

    @DisplayName("should return correct number of walls in maze")
    @Test
    public void shouldReturnTotalWallsInMaze() {
        int[][] newMaze = {{1, 0, 0}, {0, 2, 0}, {1, 1, 3}};
        Maze maze = new Maze(newMaze);
        assertEquals(3, maze.getNumberOfWalls());
    }

    @DisplayName("should throw Exception if number of entry point is more than 1")
    @Test
    public void shouldThrowExceptionIfNoOfEntryPointIsMoreThan1() {
        int[][] newMaze = {{1, 0, 2}, {0, 2, 0}, {1, 1, 3}};
        Exception exception = assertThrows(InvalidMazeException.class, () -> new Maze(newMaze));
        assertEquals("the number of entry points is incorrect :2", exception.getMessage());
    }

    @DisplayName("should throw Exception if number of entry point is 0")
    @Test
    public void shouldThrowExceptionIfNoOfEntryPointIsZero() {
        int[][] newMaze = {{1, 0, 0}, {0, 0, 0}, {1, 1, 3}};
        Exception exception = assertThrows(InvalidMazeException.class, () -> new Maze(newMaze));
        assertEquals("the number of entry points is incorrect :0", exception.getMessage());
    }

    @DisplayName("should throw Exception if number of exit point is more than 1")
    @Test
    public void shouldThrowExceptionIfNoOfExitPointIsMoreThan1() {
        int[][] newMaze = {{0, 0, 3}, {0, 3, 0}, {2, 1, 3}};
        Exception exception = assertThrows(InvalidMazeException.class, () -> new Maze(newMaze));
        assertEquals("the number of exit points is incorrect :3", exception.getMessage());
    }

    @DisplayName("should throw Exception if number of exit point is zero")
    @Test
    public void shouldThrowExceptionIfNoOfExitPointIsZero() {
        int[][] newMaze = {{0, 0, 1}, {0, 1, 0}, {2, 1, 0}};
        Exception exception = assertThrows(InvalidMazeException.class, () -> new Maze(newMaze));
        assertEquals("the number of exit points is incorrect :0", exception.getMessage());
    }

    /*
    1 1 3
    0 0 2
    1 0 0
     */
    @DisplayName("should get the entry point coordinate")
    @Test
    public void shouldGetEntryPointCoordinate() {
        int[][] newMaze = {{1, 0, 0}, {0, 0, 2}, {1, 1, 3}};
        Maze maze = new Maze(newMaze);
        assertEquals(2, maze.getEntryPoint().getX());
        assertEquals(1, maze.getEntryPoint().getY());
    }

}
