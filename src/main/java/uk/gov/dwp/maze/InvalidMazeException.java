package uk.gov.dwp.maze;

public class InvalidMazeException extends RuntimeException {

    public InvalidMazeException(String message) {
        super(message);
    }
}
