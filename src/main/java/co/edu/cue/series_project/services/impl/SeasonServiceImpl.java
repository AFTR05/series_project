package co.edu.cue.series_project.services.impl;

import co.edu.cue.series_project.domain.entities.Season;
import co.edu.cue.series_project.infrastructure.exception.SeasonException;
import co.edu.cue.series_project.infrastructure.exception.SeriesException;
import co.edu.cue.series_project.infrastructure.repositories.SeasonRepository;
import co.edu.cue.series_project.infrastructure.repositories.SeriesRepository;
import co.edu.cue.series_project.mapping.dtos.SeasonDTO;
import co.edu.cue.series_project.mapping.dtos.SeasonRequestDTO;
import co.edu.cue.series_project.mapping.dtos.SeasonUpdateDTO;
import co.edu.cue.series_project.mapping.mappers.SeasonMapper;
import co.edu.cue.series_project.services.SeasonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class SeasonServiceImpl implements SeasonService {
    private final SeasonRepository seasonRepository;
    private final SeriesRepository seriesRepository;
    private final SeasonMapper mapper;

    @Override
    public List<SeasonDTO> getAllSeason() {
        return seasonRepository.findAll()
                .stream()
                .filter(Season::getData_state)
                .map(mapper::mapFromEntity).toList();
    }

    @Override
    public SeasonDTO getOneSeason(Long id) {
        return mapper.mapFromEntity(seasonRepository.findById(id)
                .orElseThrow(() -> new SeasonException("Season with ID " + id + " not found"))
        );
    }

    @Override
    public SeasonDTO createSeason(SeasonRequestDTO seasonRequestDTO) {
        return seriesRepository.findById(seasonRequestDTO.serie_id())
                .map(series -> {
                    Season dataModification = mapper.mapFromRequestDTO(seasonRequestDTO);
                    dataModification.setSerie(series);
                    dataModification.setData_state(true);
                    try {
                        Season savedSeason = seasonRepository.save(dataModification);
                        return mapper.mapFromEntity(savedSeason);
                    } catch (Exception e) {
                        throw new SeasonException("Error al guardar el season");
                    }
                })
                .orElseThrow(() -> new SeriesException("series no encontrado"));
    }

    @Override
    public SeasonDTO updateSeason(SeasonUpdateDTO seasonUpdateInterfaceDTO) {
        return seasonRepository.findById(seasonUpdateInterfaceDTO.id())
                .map(existingSeason -> seriesRepository.findById(seasonUpdateInterfaceDTO.id())
                        .map(series -> {
                            existingSeason.setSerie(series);
                            existingSeason.setData_state(true);
                            try {
                                Season savedSeason = seasonRepository.save(existingSeason);
                                return mapper.mapFromEntity(savedSeason);
                            } catch (Exception e) {
                                throw new SeasonException("Error al guardar el season");
                            }
                        })
                        .orElseThrow(() -> new SeriesException("Series no encontrado"))
                )
                .orElseThrow(() -> new SeasonException("Season no encontrado"));
    }

    @Override
    public void deactivateSeason(Long id) {
        Season data=seasonRepository.findById(id).orElseThrow(() -> new SeasonException("Season with ID " + id + " not found"));
        data.setData_state(false);
        seasonRepository.save(data);
    }
}
