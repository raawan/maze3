package uk.gov.dwp.explorer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import uk.gov.dwp.common.Coordinate;
import uk.gov.dwp.maze.Maze;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
    DIRECTION :
           N
         W +  E
           S
*/
public class ExplorerLocationsRecordTest {

    /*
      MAZE :
        5->  1 0 0 0 1 1
        4->  1 0 2 1 0 1
        3->  0 0 0 1 0 0
        2->  0 1 1 0 1 0
        1->  0 1 3 0 1 0
        0->  0 0 0 1 0 0
             -----------
             0 1 2 3 4 5
    */
    @Test
    @DisplayName("should record the locations of explorer where explorer move forward - test 1")
    public void shouldRecordLocationsOfExplorerWhereSheHasBeen_1() {

        //Given
        Explorer explorer = getExplorer();

        //When
        explorer.moveForward();
        explorer.turnLeft();
        explorer.moveForward();

        //Then
        Set<Coordinate> expectedHaveBeenLocations = new HashSet<>();
        expectedHaveBeenLocations.add(new Coordinate(2, 4));
        expectedHaveBeenLocations.add(new Coordinate(2, 5));
        expectedHaveBeenLocations.add(new Coordinate(1, 5));
        final Set<Coordinate> actualHaveBeenLocations = explorer.getHaveBeen();

        assertTrue(expectedHaveBeenLocations.equals(actualHaveBeenLocations));
    }

    /*
      MAZE :
        5->  1 0 0 0 1 1
        4->  1 0 2 1 0 1
        3->  0 0 0 1 0 0
        2->  0 1 1 0 1 0
        1->  0 1 3 0 1 0
        0->  0 0 0 1 0 0
             -----------
             0 1 2 3 4 5
    */
    @Test
    @DisplayName("should record the locations of explorer where explorer move forward - test 2")
    public void shouldRecordLocationsOfExplorerWhereSheHasBeen_2() {

        //Given
        Explorer explorer = getExplorer();

        //When
        explorer.moveForward();
        explorer.turnLeft();
        explorer.moveForward();
        explorer.turnLeft();
        explorer.moveForward();
        explorer.moveForward();
        explorer.turnRight();
        explorer.moveForward();


        //Then
        Set<Coordinate> expectedHaveBeenLocations = new HashSet<>();
        expectedHaveBeenLocations.add(new Coordinate(2, 4));
        expectedHaveBeenLocations.add(new Coordinate(2, 5));
        expectedHaveBeenLocations.add(new Coordinate(1, 5));
        expectedHaveBeenLocations.add(new Coordinate(1, 4));
        expectedHaveBeenLocations.add(new Coordinate(1, 3));
        expectedHaveBeenLocations.add(new Coordinate(0, 3));
        final Set<Coordinate> actualHaveBeenLocations = explorer.getHaveBeen();

        assertTrue(expectedHaveBeenLocations.equals(actualHaveBeenLocations));
    }

    private Explorer getExplorer() {
        int[][] newMaze = {
                {0, 0, 0, 1, 0, 0},
                {0, 1, 3, 0, 1, 0},
                {0, 1, 1, 0, 1, 0},
                {0, 0, 0, 1, 0, 0},
                {1, 0, 2, 1, 0, 1},
                {1, 0, 0, 0, 1, 1}
        };
        Maze maze = new Maze(newMaze);
        return new Explorer(maze);
    }

}
