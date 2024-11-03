package co.edu.cue.series_project.mapping.mappers;

import co.edu.cue.series_project.domain.entities.Episode;
import co.edu.cue.series_project.domain.entities.Season;
import co.edu.cue.series_project.mapping.dtos.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface SeasonMapper {
    @Mapping(target = "episodes", source = "source.episodes")
    SeasonDTO mapFromEntity(Season source);

    @Mapping(target = "episodes", source = "source.episodes")
    Season mapFromDTO(SeasonDTO source);

    @Mapping(target = "serie.id", source = "source.serie_id")
    @Mapping(target = "episodes", source = "source.episodes")
    Season mapFromRequestDTO(SeasonRequestDTO source);

    List<SeasonDTO> mapFrom(List<Season> source);

    List<Season> mapFromListDTO(List<SeasonDTO> source);

    Season mapFromCreateDTO(CreateSeasonDTO source);
}
