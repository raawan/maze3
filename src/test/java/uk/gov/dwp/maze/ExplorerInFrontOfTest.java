package uk.gov.dwp.maze;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExplorerInFrontOfTest {

    /*
    MAZE :
        0 0 2 1 0  //3rd row
        0 1 1 0 1  //2nd row
        0 1 3 0 1  //1st row
        0 0 0 1 0  //0th row
    */

    /*
     DIRECTION :
            N
          W +  E
            S
     */

    private Explorer getRandomExplorer() {
        int[][] newMaze = {
                {0, 0, 0, 1, 0}, //0th row
                {0, 1, 3, 0, 1}, //1st row
                {0, 1, 1, 0, 1}, //2nd row
                {0, 0, 2, 1, 0}  //3rd row
        };
        Maze maze = new Maze(newMaze);
        return new Explorer(maze);
    }

    @DisplayName("Test-1: should return state in front of explorer given correct Location")
    @Test
    public void shouldReturnStateInFrontOfExplorerValueGivenCorrectLocation_1() {
        Explorer explorer = getRandomExplorer();
        explorer.setCurrentLocation(new Location(DIRECTION.S, explorer.getCurrentLocation().getCoordinate()));
        assertEquals(1, explorer.inFrontOfMe());
    }

    @DisplayName("Test-2: should return state in front of explorer given correct Location")
    @Test
    public void shouldReturnStateInFrontOfExplorerValueGivenCorrectLocation_2() {
        Explorer explorer = getRandomExplorer();
        explorer.setCurrentLocation(new Location(DIRECTION.S, new Coordinate(2, 2)));
        assertEquals(3, explorer.inFrontOfMe());
    }

    @DisplayName("Test-3: should return state in front of explorer given correct Location")
    @Test
    public void shouldReturnStateInFrontOfExplorerValueGivenCorrectLocation_3() {
        Explorer explorer = getRandomExplorer();
        explorer.setCurrentLocation(new Location(DIRECTION.E, new Coordinate(2, 2)));
        assertEquals(0, explorer.inFrontOfMe());
    }

    @DisplayName("Test-4: should return state in front of explorer given correct Location")
    @Test
    public void shouldReturnStateInFrontOfExplorerValueGivenCorrectLocation_4() {
        Explorer explorer = getRandomExplorer();
        explorer.setCurrentLocation(new Location(DIRECTION.W, new Coordinate(2, 2)));
        assertEquals(1, explorer.inFrontOfMe());
    }

    @DisplayName("should return invalid state given explorer facing north and is on top edge of maze")
    @Test
    public void shouldReturnInvalidStateGivenExplorerFacingNorthAndIsOnTopEdgeOfMaze() {
        Explorer explorer = getRandomExplorer();
        assertEquals(-1, explorer.inFrontOfMe());
    }

    @DisplayName("should return invalid state given explorer facing south and is on bottom edge of maze")
    @Test
    public void shouldReturnInvalidStateGivenExplorerFacingSouthAndIsOnBottomEdgeOfMaze() {
        Explorer explorer = getRandomExplorer();
        explorer.setCurrentLocation(new Location(DIRECTION.S, new Coordinate(3, 0)));
        assertEquals(-1, explorer.inFrontOfMe());
    }

    @DisplayName("should return invalid state given explorer facing east and is on right edge of maze")
    @Test
    public void shouldReturnInvalidStateGivenExplorerFacingEastAndIsOnRightEdgeOfMaze() {
        Explorer explorer = getRandomExplorer();
        explorer.setCurrentLocation(new Location(DIRECTION.E, new Coordinate(4, 2)));
        assertEquals(-1, explorer.inFrontOfMe());
    }

    @DisplayName("should return invalid state given explorer facing west and is on top left edge of maze")
    @Test
    public void shouldReturnInvalidStateGivenExplorerFacingWestAndIsOnLeftEdgeOfMaze() {
        Explorer explorer = getRandomExplorer();
        explorer.setCurrentLocation(new Location(DIRECTION.W, new Coordinate(0, 3)));
        assertEquals(-1, explorer.inFrontOfMe());
    }


}
