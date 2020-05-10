package uk.gov.dwp.explorer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import uk.gov.dwp.common.Coordinate;
import uk.gov.dwp.common.DIRECTION;
import uk.gov.dwp.common.Location;
import uk.gov.dwp.maze.Maze;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/*
    DIRECTION :
           N
         W +  E
           S
*/
public class ExplorerMoveForwardTest {

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
    @ParameterizedTest
    @CsvSource({
            "N,2,4,2,5",
            "W,1,3,0,3",
    })
    @DisplayName("should move forward if SPACE is available in front of explorer")
    public void shouldMoveForwardIfSpaceIsAvailableInFrontOfExplorer(
            String direction, int currentLocationX, int currentLocationY,
            int expectedLocationX, int expectedLocationY) {

        //Given
        Explorer explorer = getExplorer();
        explorer.setCurrentLocation(new Location(DIRECTION.valueOf(direction), new Coordinate(currentLocationX, currentLocationY)));

        //When
        explorer.moveForward();

        //Then
        final Coordinate coordinate = explorer.getCurrentLocation().getCoordinate();
        assertEquals(expectedLocationX, coordinate.getX());
        assertEquals(expectedLocationY, coordinate.getY());
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
    @DisplayName("should not move forward if ENTRY is available in front of explorer")
    public void shouldNotMoveForwardIfEntryIsAvailableInFrontOfExplorer() {
        //Given
        Explorer explorer = getExplorer();
        explorer.setCurrentLocation(new Location(DIRECTION.N, new Coordinate(2, 3)));

        //When
        explorer.moveForward();

        //Then
        final Coordinate coordinate = explorer.getCurrentLocation().getCoordinate();
        assertEquals(2, coordinate.getX());
        assertEquals(3, coordinate.getY());

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
    @DisplayName("should move forward if WALL is available in front of explorer")
    public void shouldNotMoveForwardIfWallIsAvailableInFrontOfExplorer() {
        //Given
        Explorer explorer = getExplorer();
        explorer.setCurrentLocation(new Location(DIRECTION.N, new Coordinate(3, 2)));

        //When
        explorer.moveForward();

        //Then
        final Coordinate coordinate = explorer.getCurrentLocation().getCoordinate();
        assertEquals(3, coordinate.getX());
        assertEquals(2, coordinate.getY());

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
