package uk.gov.dwp.explorer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import uk.gov.dwp.common.Coordinate;
import uk.gov.dwp.maze.Maze;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
    public void shouldRecordLocationsOfExplorerWhereSheHasBeen_1() {

        //Given
        Explorer explorer = getExplorer();

        //When
        explorer.moveForward();
        explorer.turnLeft();
        explorer.moveForward();

        //Then
        List<Coordinate> expectedHaveBeenLocations = new ArrayList<>();
        expectedHaveBeenLocations.add(new Coordinate(2, 4));
        expectedHaveBeenLocations.add(new Coordinate(2, 5));
        expectedHaveBeenLocations.add(new Coordinate(1, 5));
        final List<Coordinate> actualHaveBeenLocations = explorer.getHaveBeen();

        assertTrue(listEqualsIgnoreOrder(expectedHaveBeenLocations, actualHaveBeenLocations));
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
        List<Coordinate> expectedHaveBeenLocations = new ArrayList<>();
        expectedHaveBeenLocations.add(new Coordinate(2, 4));
        expectedHaveBeenLocations.add(new Coordinate(2, 5));
        expectedHaveBeenLocations.add(new Coordinate(1, 5));
        expectedHaveBeenLocations.add(new Coordinate(1, 4));
        expectedHaveBeenLocations.add(new Coordinate(1, 3));
        expectedHaveBeenLocations.add(new Coordinate(0, 3));
        final List<Coordinate> actualHaveBeenLocations = explorer.getHaveBeen();

        assertTrue(listEqualsIgnoreOrder(expectedHaveBeenLocations, actualHaveBeenLocations));
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

    private boolean listEqualsIgnoreOrder(List<Coordinate> list1, List<Coordinate> list2) {
        return new HashSet<>(list1).equals(new HashSet<>(list2));
    }
}
