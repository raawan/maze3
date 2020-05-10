package uk.gov.dwp.common;

import java.util.Objects;

public final class Location {

    private final DIRECTION direction;
    private final Coordinate coordinate;

    public Location(final DIRECTION direction, final Coordinate coordinate) {
        this.direction = direction;
        this.coordinate = coordinate;
    }

    public Location(final DIRECTION direction, final int x, final int y) {
        this.direction = direction;
        this.coordinate = new Coordinate(x, y);
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

