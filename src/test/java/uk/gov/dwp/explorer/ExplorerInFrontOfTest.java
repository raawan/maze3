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
public class ExplorerInFrontOfTest {

    /*
    MAZE :
      3->  0 0 2 1 0
      2->  0 1 1 0 1
      1->  0 1 3 0 1
      0->  0 0 0 1 0
           ---------
           0 1 2 3 4
    */
    @DisplayName("should return state in front of explorer given correct Location")
    @ParameterizedTest
    @CsvSource({
            "S,2,3,1",
            "S,2,2,3",
            "E,2,2,0",
            "W,2,2,1"
    })
    public void shouldReturnStateInFrontOfExplorerValueGivenCorrectLocation(
            String direction, int currentLocationX, int currentLocationY,
            int expectedStateInFrontOfExplorer
    ) {
        Explorer explorer = getExplorer();
        explorer.setCurrentLocation(new Location(DIRECTION.valueOf(direction), new Coordinate(currentLocationX, currentLocationY)));
        assertEquals(expectedStateInFrontOfExplorer, explorer.inFrontOfMe());
    }

    /*
    MAZE :
      3->  0 0 2 1 0
      2->  0 1 1 0 1
      1->  0 1 3 0 1
      0->  0 0 0 1 0
           ---------
           0 1 2 3 4
    */
    @DisplayName("should return invalid state given explorer facing north and is on top edge of maze")
    @Test
    public void shouldReturnInvalidStateGivenExplorerFacingNorthAndIsOnTopEdgeOfMaze() {
        Explorer explorer = getExplorer();
        assertEquals(-1, explorer.inFrontOfMe());
    }

    /*
    MAZE :
      3->  0 0 2 1 0
      2->  0 1 1 0 1
      1->  0 1 3 0 1
      0->  0 0 0 1 0
           ---------
           0 1 2 3 4
    */
    @DisplayName("should return invalid state given explorer facing south and is on bottom edge of maze")
    @Test
    public void shouldReturnInvalidStateGivenExplorerFacingSouthAndIsOnBottomEdgeOfMaze() {
        Explorer explorer = getExplorer();
        explorer.setCurrentLocation(new Location(DIRECTION.S, new Coordinate(3, 0)));
        assertEquals(-1, explorer.inFrontOfMe());
    }

    /*
    MAZE :
      3->  0 0 2 1 0
      2->  0 1 1 0 1
      1->  0 1 3 0 1
      0->  0 0 0 1 0
           ---------
           0 1 2 3 4
    */
    @DisplayName("should return invalid state given explorer facing east and is on right edge of maze")
    @Test
    public void shouldReturnInvalidStateGivenExplorerFacingEastAndIsOnRightEdgeOfMaze() {
        Explorer explorer = getExplorer();
        explorer.setCurrentLocation(new Location(DIRECTION.E, new Coordinate(4, 2)));
        assertEquals(-1, explorer.inFrontOfMe());
    }

    /*
    MAZE :
      3->  0 0 2 1 0
      2->  0 1 1 0 1
      1->  0 1 3 0 1
      0->  0 0 0 1 0
           ---------
           0 1 2 3 4
    */
    @DisplayName("should return invalid state given explorer facing west and is on top left edge of maze")
    @Test
    public void shouldReturnInvalidStateGivenExplorerFacingWestAndIsOnLeftEdgeOfMaze() {
        Explorer explorer = getExplorer();
        explorer.setCurrentLocation(new Location(DIRECTION.W, new Coordinate(0, 3)));
        assertEquals(-1, explorer.inFrontOfMe());
    }

    private Explorer getExplorer() {
        int[][] newMaze = {
                {0, 0, 0, 1, 0}, //0th row
                {0, 1, 3, 0, 1}, //1st row
                {0, 1, 1, 0, 1}, //2nd row
                {0, 0, 2, 1, 0}  //3rd row
        };
        Maze maze = new Maze(newMaze);
        return new Explorer(maze);
    }
}
