package uk.gov.dwp.maze;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ExplorerAvailableMovementOptionsTest {
    /*
     DIRECTION :
            N
          W +  E
            S
     */
    /*
    MAZE :
        0 0 2 1 0
        0 1 1 0 1
        0 1 3 0 1
        0 0 0 1 0
    */

    private Explorer getRandomExplorer() {
        int[][] newMaze = {
                {0, 0, 0, 1, 0, 0},
                {0, 1, 3, 0, 1, 0},
                {0, 1, 1, 0, 1, 0},
                {0, 0, 2, 1, 0, 0},
                {0, 0, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 1}
        };
        Maze maze = new Maze(newMaze);
        return new Explorer(maze);
    }

    /*
   MAZE :
       1 0 1 0 1 1
       0 0 0 1 0 1
       0 0 2 1 0 0
       0 1 1 0 1 0
       0 1 3 0 1 0
       0 0 0 1 0 0
   */

    /*
    ToDo:
    You shouldnt be able to set location on a WALL
     */
    @Test
    public void shouldReturnAvailableMovementOptionsGivenCorrectLocationInMaze() {
        Explorer explorer = getRandomExplorer();
        explorer.setCurrentLocation(new Location(DIRECTION.S, new Coordinate(4, 3)));

        final List<Location> expectedAvailableMovementOptions = new ArrayList<>();
        expectedAvailableMovementOptions.add(new Location(explorer.getCurrentLocation().getDirection(),new Coordinate(4,4)));
        expectedAvailableMovementOptions.add(new Location(explorer.getCurrentLocation().getDirection(),new Coordinate(5,3)));

        final List<Location> availableMovementOptions = explorer.availableMovementOptions();
        assertEquals(2,availableMovementOptions.size());

        assertTrue(listEqualsIgnoreOrder(expectedAvailableMovementOptions,availableMovementOptions));
    }

    public boolean listEqualsIgnoreOrder(List<Location> list1, List<Location> list2) {
        return new HashSet<>(list1).equals(new HashSet<>(list2));
    }

}
