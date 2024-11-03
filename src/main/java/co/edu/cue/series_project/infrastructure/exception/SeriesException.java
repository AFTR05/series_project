package co.edu.cue.series_project.infrastructure.exception;

import java.util.NoSuchElementException;

public class SeriesException extends NoSuchElementException {
    public SeriesException(String message) {
        super(message);
    }
}
