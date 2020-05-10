package uk.gov.dwp.explorer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static uk.gov.dwp.explorer.ExplorerHelper.getExplorerWithNewEntryLocation;

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

    public static final int[][] NEW_MAZE = new int[][]{
            {0, 0, 0, 1, 0}, //0th row
            {0, 1, 3, 0, 1}, //1st row
            {0, 1, 1, 0, 1}, //2nd row
            {0, 0, 2, 1, 0}  //3rd row
    };

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
        final Explorer explorer = getExplorerWithNewEntryLocation(new Maze(NEW_MAZE), new Location(DIRECTION.valueOf(direction), currentLocationX,currentLocationY));
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
        final Explorer explorer = getExplorerWithNewEntryLocation(new Maze(NEW_MAZE), new Location(DIRECTION.N, 3,3));
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
        final Explorer explorer = getExplorerWithNewEntryLocation(new Maze(NEW_MAZE), new Location(DIRECTION.S, 3,0));
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
        final Explorer explorer = getExplorerWithNewEntryLocation(new Maze(NEW_MAZE), new Location(DIRECTION.E, 4,2));
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
        final Explorer explorer = getExplorerWithNewEntryLocation(new Maze(NEW_MAZE), new Location(DIRECTION.W, 0,3));
        assertEquals(-1, explorer.inFrontOfMe());
    }

}
