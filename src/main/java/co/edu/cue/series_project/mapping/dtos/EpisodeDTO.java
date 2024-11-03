package co.edu.cue.series_project.mapping.dtos;

import co.edu.cue.series_project.domain.entities.Season;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Duration;
import java.util.Date;

public record EpisodeDTO(
        @JsonProperty("title") String title,
        @JsonProperty("description") String description,
        @JsonProperty("duration") Duration duration,
        @JsonProperty("startDate") Date startDate,
        @JsonProperty("season") Season season,
        @JsonProperty("data_state") Boolean data_state

){
}
