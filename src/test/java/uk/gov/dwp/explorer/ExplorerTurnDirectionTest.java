package uk.gov.dwp.explorer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import uk.gov.dwp.common.Coordinate;
import uk.gov.dwp.common.DIRECTION;
import uk.gov.dwp.common.Location;
import uk.gov.dwp.maze.Maze;

import org.junit.jupiter.api.Assertions;
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
public class ExplorerTurnDirectionTest {

    @Test
    @DisplayName("should return correct starting DIRECTION and CO-ORDINATE of explorer")
    public void shouldReturnCorrectStartingDirectionAndCoordinate() {
        //Given
        Explorer explorer = getExplorer();

        //Then
        Assertions.assertEquals(DIRECTION.N, explorer.getCurrentLocation().getDirection());
        Assertions.assertEquals(new Coordinate(2, 1), explorer.getCurrentLocation().getCoordinate());
    }

    @ParameterizedTest
    @CsvSource({
            "N,W",
            "S,E",
            "E,N",
            "W,S"
    })
    @DisplayName("should return correct current direction of explorer after turning LEFT")
    public void shouldReturnCorrectDirectionOnTurnLeft(String currentDirection,
                                                       String expectedDirectionAfterTurningLeft) {

        //Given
        Explorer explorer = getExplorer();
        explorer.setCurrentLocation(new Location(DIRECTION.valueOf(currentDirection), explorer.getCurrentLocation().getCoordinate()));
        assertEquals(DIRECTION.valueOf(currentDirection), explorer.getCurrentLocation().getDirection());
        //When
        explorer.turnLeft();
        //Then
        assertEquals(DIRECTION.valueOf(expectedDirectionAfterTurningLeft), explorer.getCurrentLocation().getDirection());
    }


    @ParameterizedTest
    @CsvSource({
            "N,E",
            "S,W",
            "E,S",
            "W,N"
    })
    @DisplayName("should return correct current direction of explorer after turning RIGHT")
    public void shouldReturnCorrectDirectionOnTurnRight(String currentDirection,
                                                        String expectedDirectionAfterTurningLeft) {

        //Given
        Explorer explorer = getExplorer();
        explorer.setCurrentLocation(new Location(DIRECTION.valueOf(currentDirection), explorer.getCurrentLocation().getCoordinate()));
        assertEquals(DIRECTION.valueOf(currentDirection), explorer.getCurrentLocation().getDirection());
        //When
        explorer.turnRight();
        //Then
        assertEquals(DIRECTION.valueOf(expectedDirectionAfterTurningLeft), explorer.getCurrentLocation().getDirection());
    }

    private Explorer getExplorer() {
        int[][] newMaze = {{1, 0, 0}, {0, 0, 2}, {1, 1, 3}};
        Maze maze = new Maze(newMaze);
        return new Explorer(maze);
    }
}
