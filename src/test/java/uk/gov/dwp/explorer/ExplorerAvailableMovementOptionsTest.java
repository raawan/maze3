package uk.gov.dwp.explorer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static uk.gov.dwp.explorer.helper.ExplorerHelper.getExplorerWithNewEntryLocation;

import uk.gov.dwp.common.DIRECTION;
import uk.gov.dwp.common.Location;
import uk.gov.dwp.maze.Maze;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
    DIRECTION :
           N
         W +  E
           S
*/
public class ExplorerAvailableMovementOptionsTest {

    public static final int[][] NEW_MAZE = new int[][]{
            {0, 0, 0, 1, 0, 0},
            {0, 1, 3, 0, 1, 0},
            {0, 1, 1, 0, 1, 0},
            {0, 0, 2, 1, 0, 0},
            {0, 0, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 1}
    };

    /*
              MAZE :
                5->  1 0 1 0 1 1
                4->  0 0 0 1 0 1
                3->  0 0 2 1 0 0
                2->  0 1 1 0 1 0
                1->  0 1 3 0 1 0
                0->  0 0 0 1 0 0
                     -----------
                     0 1 2 3 4 5
            */
    @Test
    @DisplayName("should return available movement options to explorer from his current position - test 1")
    public void shouldReturnAvailableMovementOptionsGivenCorrectLocationInMaze_1() {
        //Given
        final Explorer explorer = getExplorerWithNewEntryLocation(new Maze(NEW_MAZE), new Location(DIRECTION.S, 4,3));
        final List<Location> expectedAvailableMovementOptions = new ArrayList<>();
        expectedAvailableMovementOptions.add(new Location(explorer.getCurrentLocation().getDirection(), 4,4));
        expectedAvailableMovementOptions.add(new Location(explorer.getCurrentLocation().getDirection(), 5,3));

        //When
        final List<Location> availableMovementOptions = explorer.availableMovementOptions();

        //Then
        assertEquals(2, availableMovementOptions.size());
        assertTrue(listEqualsIgnoreOrder(expectedAvailableMovementOptions, availableMovementOptions));
    }

    /*
      MAZE :
        5->  1 0 1 0 1 1
        4->  0 0 0 1 0 1
        3->  0 0 2 1 0 0
        2->  0 1 1 0 1 0
        1->  0 1 3 0 1 0
        0->  0 0 0 1 0 0
             -----------
             0 1 2 3 4 5
    */
    @Test
    @DisplayName("should return available movement options to explorer from his current position - test 2")
    public void shouldReturnAvailableMovementOptionsGivenCorrectLocationInMaze_2() {
        //Given
        final Explorer explorer = getExplorerWithNewEntryLocation(new Maze(NEW_MAZE), new Location(DIRECTION.S,0,3));
        final List<Location> expectedAvailableMovementOptions = new ArrayList<>();
        expectedAvailableMovementOptions.add(new Location(explorer.getCurrentLocation().getDirection(), 1,3));
        expectedAvailableMovementOptions.add(new Location(explorer.getCurrentLocation().getDirection(),0,2));
        expectedAvailableMovementOptions.add(new Location(explorer.getCurrentLocation().getDirection(), 0,4));

        //When
        final List<Location> availableMovementOptions = explorer.availableMovementOptions();

        //Then
        assertEquals(3, availableMovementOptions.size());
        assertTrue(listEqualsIgnoreOrder(expectedAvailableMovementOptions, availableMovementOptions));
    }

    /*
    MAZE :
      5->  1 0 1 0 1 1
      4->  0 0 0 1 0 1
      3->  0 0 2 1 0 0
      2->  0 1 1 0 1 0
      1->  0 1 3 0 1 0
      0->  0 0 0 1 0 0
           -----------
           0 1 2 3 4 5
  */
    @Test
    @DisplayName("should return available movement options to explorer from his current position - test 3")
    public void shouldReturnAvailableMovementOptionsGivenCorrectLocationInMaze_3() {
        //Given
        final Explorer explorer = getExplorerWithNewEntryLocation(new Maze(NEW_MAZE), new Location(DIRECTION.S, 5,0));
        final List<Location> expectedAvailableMovementOptions = new ArrayList<>();
        expectedAvailableMovementOptions.add(new Location(explorer.getCurrentLocation().getDirection(), 4,0));
        expectedAvailableMovementOptions.add(new Location(explorer.getCurrentLocation().getDirection(), 5,1));

        //When
        final List<Location> availableMovementOptions = explorer.availableMovementOptions();

        //Then
        assertEquals(2, availableMovementOptions.size());
        assertTrue(listEqualsIgnoreOrder(expectedAvailableMovementOptions, availableMovementOptions));
    }

    /*
    MAZE :
      5->  1 0 1 0 1 1
      4->  0 0 0 1 0 1
      3->  0 0 2 1 0 0
      2->  0 1 1 0 1 0
      1->  0 1 3 0 1 0
      0->  0 0 0 1 0 0
           -----------
           0 1 2 3 4 5
    */
    @Test
    @DisplayName("should return available movement options to explorer from his current position - test 4")
    public void shouldReturnAvailableMovementOptionsGivenCorrectLocationInMaze_4() {
        //Given
        final Explorer explorer = getExplorerWithNewEntryLocation(new Maze(NEW_MAZE), new Location(DIRECTION.S, 4,4));
        final List<Location> expectedAvailableMovementOptions = new ArrayList<>();
        expectedAvailableMovementOptions.add(new Location(explorer.getCurrentLocation().getDirection(), 4,3));

        //When
        final List<Location> availableMovementOptions = explorer.availableMovementOptions();

        //Then
        assertEquals(1, availableMovementOptions.size());
        assertTrue(listEqualsIgnoreOrder(expectedAvailableMovementOptions, availableMovementOptions));
    }

    /*
    MAZE :
      5->  1 0 1 0 1 1
      4->  0 0 0 1 0 1
      3->  0 0 2 1 0 0
      2->  0 1 1 0 1 0
      1->  0 1 3 0 1 0
      0->  0 0 0 1 0 0
           -----------
           0 1 2 3 4 5
    */
    @Test
    @DisplayName("should return available movement options to explorer from his current position - test 5")
    public void shouldReturnAvailableMovementOptionsGivenCorrectLocationInMaze_5() {
        //Given
        final Explorer explorer = getExplorerWithNewEntryLocation(new Maze(NEW_MAZE), new Location(DIRECTION.S,3,5));
        final List<Location> expectedAvailableMovementOptions = new ArrayList<>();

        //When
        final List<Location> availableMovementOptions = explorer.availableMovementOptions();

        //Then
        assertEquals(0, availableMovementOptions.size());
    }

    private boolean listEqualsIgnoreOrder(List<Location> list1, List<Location> list2) {
        return new HashSet<>(list1).equals(new HashSet<>(list2));
    }

}
