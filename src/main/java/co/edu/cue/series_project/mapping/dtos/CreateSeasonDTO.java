package co.edu.cue.series_project.mapping.dtos;

import co.edu.cue.series_project.domain.entities.Episode;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public record CreateSeasonDTO (
        @JsonProperty("id") Long id,
        @JsonProperty("serie_id") Long serie_id,
        @JsonProperty("seasonNumber") Integer seasonNumber,
        @JsonProperty("startDate") Date startDate,
        @JsonProperty("episodes") List<Episode> episodes,
        @JsonProperty("data_state") Boolean data_state
) {
}
