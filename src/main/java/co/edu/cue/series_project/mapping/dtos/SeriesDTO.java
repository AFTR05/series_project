package co.edu.cue.series_project.mapping.dtos;

import co.edu.cue.series_project.domain.entities.Season;
import co.edu.cue.series_project.domain.enums.State;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public record SeriesDTO(
        @JsonProperty("title") String title,
        @JsonProperty("description") String description,
        @JsonProperty("startDate") Date startDate,
        @JsonProperty("seasons") List<Season> seasons,
        @JsonProperty("qualification") Double qualification,
        @JsonProperty("state") State state,
        @JsonProperty("data_state") Boolean data_state
) { }
