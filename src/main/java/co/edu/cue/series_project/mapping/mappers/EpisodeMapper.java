package co.edu.cue.series_project.mapping.mappers;

import co.edu.cue.series_project.domain.entities.Episode;
import co.edu.cue.series_project.mapping.dtos.EpisodeDTO;
import co.edu.cue.series_project.mapping.dtos.EpisodeRequestDTO;
import org.springframework.stereotype.Component;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "spring")
@Component
public interface EpisodeMapper {
    EpisodeDTO mapFromEntity(Episode source);

    Episode mapFromDTO(EpisodeDTO source);

    @Mapping(target = "season.id", source = "source.season_id")
    Episode mapFromRequestDTO(EpisodeRequestDTO source);

    List<EpisodeDTO> mapFrom(List<Episode> source);

    List<Episode> mapFromListDTO(List<EpisodeDTO> source);

}
