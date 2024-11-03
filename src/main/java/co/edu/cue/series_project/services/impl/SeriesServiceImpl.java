package co.edu.cue.series_project.services.impl;

import co.edu.cue.series_project.domain.entities.Serie;
import co.edu.cue.series_project.infrastructure.exception.SeriesException;
import co.edu.cue.series_project.infrastructure.repositories.SeriesRepository;
import co.edu.cue.series_project.mapping.dtos.SeriesDTO;
import co.edu.cue.series_project.mapping.dtos.SeriesRequestDTO;
import co.edu.cue.series_project.mapping.mappers.SeriesMapper;
import co.edu.cue.series_project.services.SeriesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class SeriesServiceImpl implements SeriesService {
    private final SeriesRepository seriesRepository;
    private final SeriesMapper mapper;

    @Override
    public List<SeriesDTO> getAllSeries() {
        return seriesRepository.findAll()
                .stream()
                .filter(Serie::getData_state)
                .map(mapper::mapFromEntity).toList();
    }

    @Override
    public SeriesDTO getOneSeries(Long id) {
        return mapper.mapFromEntity(seriesRepository.findById(id)
                .orElseThrow(() -> new SeriesException("Series with ID " + id + " not found"))
        );
    }

    @Override
    public SeriesDTO createSeries(SeriesRequestDTO seriesRequestDTO) {
        Serie dataModification = mapper.mapFromRequestDTO(seriesRequestDTO);
        try {
            Serie savedReward = seriesRepository.save(dataModification);
            return mapper.mapFromEntity(savedReward);
        } catch (Exception e) {
            throw new SeriesException("Error al guardar la Season");
        }
    }


    @Override
    public void deactivateSeries(Long id) {
        Serie data=seriesRepository.findById(id).orElseThrow(() -> new SeriesException("Series with ID " + id + " not found"));
        data.setData_state(false);
        seriesRepository.save(data);
    }
}
