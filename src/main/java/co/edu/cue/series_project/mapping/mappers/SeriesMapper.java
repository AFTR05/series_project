package co.edu.cue.series_project.mapping.mappers;

import co.edu.cue.series_project.domain.entities.Serie;
import co.edu.cue.series_project.mapping.dtos.SeriesDTO;
import co.edu.cue.series_project.mapping.dtos.SeriesRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper(componentModel = "spring")
@Component
public interface SeriesMapper {
    @Mapping(target = "seasons", source = "source.seasons")
    SeriesDTO mapFromEntity(Serie source);

    @Mapping(target = "seasons", source = "source.seasons")
    Serie mapFromDTO(SeriesDTO source);

    @Mapping(target = "seasons", source = "source.seasons")
    Serie mapFromRequestDTO(SeriesRequestDTO source);

    List<SeriesDTO> mapFrom(List<Serie> source);

    List<Serie> mapFromListDTO(List<SeriesDTO> source);
}
