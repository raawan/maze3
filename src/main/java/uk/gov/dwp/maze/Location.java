package uk.gov.dwp.maze;

import java.util.Objects;

public class Location {

    private final DIRECTION direction;
    private final Coordinate coordinate;

    public Location(final DIRECTION direction, final Coordinate coordinate) {
        this.direction = direction;
        this.coordinate = coordinate;
    }

    public DIRECTION getDirection() {
        return direction;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        final Location location = (Location) o;
        return direction.equals(location.direction) &&
                Objects.equals(coordinate, location.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction, coordinate);
    }
}

enum DIRECTION {N, S, E, W}

final class Coordinate {

    private final int x;
    private final int y;

    public Coordinate(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Coordinate that = (Coordinate) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
