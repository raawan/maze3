package uk.gov.dwp.maze;

//* Given a maze the explorer should be able to drop in to the Start point (facing north)
//* An explorer on a maze must be able to move forward
//* An explorer on a maze must be able to turn left and right (changing direction the explorer is facing)
//* An explorer on a maze must be able to declare what is in front of them
//* An explorer on a maze must be able to declare all movement options from their given location
//* An explorer on a maze must be able to report a record of where they have been in an understandable fashion

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Explorer {

    private final Maze maze;
    private final int haveBeen[][];
    private Location currentLocation;

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public Maze getMaze() {
        return maze;
    }

    public void setCurrentLocation(final Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Explorer(final Maze maze) {
        this.maze = maze;
        this.haveBeen = new int[maze.getMaze().length][maze.getMaze()[0].length];
        currentLocation = new Location(DIRECTION.N, maze.getEntryPoint());
    }

    void moveForward() {
        int x = currentLocation.getCoordinate().getX();
        int y = currentLocation.getCoordinate().getY();
        final List<Location> locations = availableLocationsToMove(x, y).stream()
                .filter(location -> currentLocation.getDirection().equals(location.getDirection()))
                .filter(location -> isValidMove(location.getCoordinate().getX(), location.getCoordinate().getY()))
                .collect(Collectors.toList());
        currentLocation = locations.get(0);
    }

    /*
    This method determines only one step movement options instead of the whole path
    The assumption made here is that if its an exit point or a space next its a valid move
     */
    List<Location> availableMovementOptions() {
        int x = currentLocation.getCoordinate().getX();
        int y = currentLocation.getCoordinate().getY();

        List<Location> availableLocations = new ArrayList<>();
        availableLocationsToMove(x, y).stream()
                .map(location -> location.getCoordinate())
                .filter(coordinate -> isValidMove(coordinate.getX(), coordinate.getY()))
                .forEach(coordinate -> availableLocations.add(new Location(currentLocation.getDirection(), coordinate)));

        return availableLocations;
    }


    void turnLeft() {
        switch (currentLocation.getDirection()) {
            case N:
                currentLocation = new Location(DIRECTION.W, currentLocation.getCoordinate());
                break;
            case S:
                currentLocation = new Location(DIRECTION.E, currentLocation.getCoordinate());
                break;
            case E:
                currentLocation = new Location(DIRECTION.N, currentLocation.getCoordinate());
                break;
            case W:
                currentLocation = new Location(DIRECTION.S, currentLocation.getCoordinate());
                break;
        }
    }

    void turnRight() {
        switch (currentLocation.getDirection()) {
            case N:
                currentLocation = new Location(DIRECTION.E, currentLocation.getCoordinate());
                break;
            case S:
                currentLocation = new Location(DIRECTION.W, currentLocation.getCoordinate());
                break;
            case E:
                currentLocation = new Location(DIRECTION.S, currentLocation.getCoordinate());
                break;
            case W:
                currentLocation = new Location(DIRECTION.N, currentLocation.getCoordinate());
                break;
        }
    }

    /*
   This method will return -1 if the explorer is on border and facing away from Maze
   */
    int inFrontOfMe() {
        int x = currentLocation.getCoordinate().getX();
        int y = currentLocation.getCoordinate().getY();

        final List<Integer> stateInFrontOfMe = availableLocationsToMove(x, y).stream()
                .filter(location -> currentLocation.getDirection().equals(location.getDirection()))
                .map(location -> determineInFrontOfMe(location.getCoordinate().getX(), location.getCoordinate().getY()))
                .collect(Collectors.toList());

        return stateInFrontOfMe.get(0);
    }

    private int determineInFrontOfMe(final int x, final int y) {
        if (isOutsideMazeBorder(x, y)) {
            return -1;
        } else {
            return maze.getMaze()[y][x];
        }
    }

    private boolean isValidMove(final int x, final int y) {
        if (isOutsideMazeBorder(x, y)) {
            return false;
        }
        if (maze.getMaze()[y][x] == Maze.WALL || maze.getMaze()[y][x] == Maze.ENTRY) {
            return false;
        }
        return true;
    }

    private boolean isOutsideMazeBorder(final int x, final int y) {
        int height = maze.getMaze().length;
        int width = maze.getMaze()[0].length;
        if (x < 0 || x > width - 1 || y < 0 || y > height - 1) {
            return true;
        }
        return false;
    }

    private List<Location> availableLocationsToMove(int x, int y) {
        List<Location> locations = new ArrayList<>();
        locations.add(new Location(DIRECTION.N, new Coordinate(x, y + 1))); // Move UP
        locations.add(new Location(DIRECTION.S, new Coordinate(x, y - 1))); // Move Down
        locations.add(new Location(DIRECTION.E, new Coordinate(x + 1, y))); // Move RIGHT
        locations.add(new Location(DIRECTION.W, new Coordinate(x - 1, y))); // Move LEFT
        return locations;
    }

    public void traverseMaze() {

    }

}
