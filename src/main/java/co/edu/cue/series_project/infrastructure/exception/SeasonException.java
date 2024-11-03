package co.edu.cue.series_project.infrastructure.exception;

import java.util.NoSuchElementException;

public class SeasonException extends NoSuchElementException {
    public SeasonException(String message) {
        super(message);
    }
}
