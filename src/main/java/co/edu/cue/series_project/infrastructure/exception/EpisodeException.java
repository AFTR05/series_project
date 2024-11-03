package co.edu.cue.series_project.infrastructure.exception;

import java.util.NoSuchElementException;

public class EpisodeException extends NoSuchElementException {
    public EpisodeException(String message) {
        super(message);
    }
}
