package uk.gov.dwp.maze;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ExplorerTurnDirectionTest {

    @Test
    public void shouldReturnCorrectStartingDirectionAndCoordinate() {
        Explorer explorer = getRandomExplorer();
        assertEquals(DIRECTION.N, explorer.getCurrentLocation().getDirection());
        assertEquals(new Coordinate(2, 1), explorer.getCurrentLocation().getCoordinate());
    }

    @Test
    public void shouldReturnWestOnTurnLeftWhenFacingNorth() {
        Explorer explorer = getRandomExplorer();
        assertEquals(DIRECTION.N, explorer.getCurrentLocation().getDirection());
        explorer.turnLeft();
        assertEquals(DIRECTION.W, explorer.getCurrentLocation().getDirection());
    }

    @Test
    public void shouldReturnEastOnTurnLeftWhenFacingSouth() {
        Explorer explorer = getRandomExplorer();
        explorer.setCurrentLocation(new Location(DIRECTION.S,explorer.getCurrentLocation().getCoordinate()));
        assertEquals(DIRECTION.S, explorer.getCurrentLocation().getDirection());
        explorer.turnLeft();
        assertEquals(DIRECTION.E, explorer.getCurrentLocation().getDirection());
    }

    @Test
    public void shouldReturnNorthOnTurnLeftWhenFacingEast() {
        Explorer explorer = getRandomExplorer();
        explorer.setCurrentLocation(new Location(DIRECTION.E,explorer.getCurrentLocation().getCoordinate()));
        assertEquals(DIRECTION.E, explorer.getCurrentLocation().getDirection());
        explorer.turnLeft();
        assertEquals(DIRECTION.N, explorer.getCurrentLocation().getDirection());
    }

    @Test
    public void shouldReturnSouthOnTurnLeftWhenFacingWest() {
        Explorer explorer = getRandomExplorer();
        explorer.setCurrentLocation(new Location(DIRECTION.W,explorer.getCurrentLocation().getCoordinate()));
        assertEquals(DIRECTION.W, explorer.getCurrentLocation().getDirection());
        explorer.turnLeft();
        assertEquals(DIRECTION.S, explorer.getCurrentLocation().getDirection());
    }

    @Test
    public void shouldReturnEastOnTurnRightWhenFacingNorth() {
        Explorer explorer = getRandomExplorer();
        assertEquals(DIRECTION.N, explorer.getCurrentLocation().getDirection());
        explorer.turnRight();
        assertEquals(DIRECTION.E, explorer.getCurrentLocation().getDirection());
    }


    @Test
    public void shouldReturnWestOnTurnRightWhenFacingSouth() {
        Explorer explorer = getRandomExplorer();
        explorer.setCurrentLocation(new Location(DIRECTION.S,explorer.getCurrentLocation().getCoordinate()));
        assertEquals(DIRECTION.S, explorer.getCurrentLocation().getDirection());
        explorer.turnRight();
        assertEquals(DIRECTION.W, explorer.getCurrentLocation().getDirection());
    }

    @Test
    public void shouldReturnSouthOnTurnRightWhenFacingEast() {
        Explorer explorer = getRandomExplorer();
        explorer.setCurrentLocation(new Location(DIRECTION.E,explorer.getCurrentLocation().getCoordinate()));
        assertEquals(DIRECTION.E, explorer.getCurrentLocation().getDirection());
        explorer.turnRight();
        assertEquals(DIRECTION.S, explorer.getCurrentLocation().getDirection());
    }

    @Test
    public void shouldReturnNorthOnTurnRightWhenFacingWest() {
        Explorer explorer = getRandomExplorer();
        explorer.setCurrentLocation(new Location(DIRECTION.W,explorer.getCurrentLocation().getCoordinate()));
        assertEquals(DIRECTION.W, explorer.getCurrentLocation().getDirection());
        explorer.turnRight();
        assertEquals(DIRECTION.N, explorer.getCurrentLocation().getDirection());
    }

    private Explorer getRandomExplorer() {
        int[][] newMaze = {{1, 0, 0}, {0, 0, 2}, {1, 1, 3}};
        Maze maze = new Maze(newMaze);
        return new Explorer(maze);
    }
}
