package co.edu.cue.series_project.services.impl;

import co.edu.cue.series_project.domain.entities.Episode;
import co.edu.cue.series_project.infrastructure.exception.EpisodeException;
import co.edu.cue.series_project.infrastructure.exception.SeasonException;
import co.edu.cue.series_project.infrastructure.repositories.EpisodeRepository;
import co.edu.cue.series_project.infrastructure.repositories.SeasonRepository;
import co.edu.cue.series_project.infrastructure.utils.ImageUtil;
import co.edu.cue.series_project.mapping.dtos.EpisodeDTO;
import co.edu.cue.series_project.mapping.dtos.EpisodeRequestDTO;
import co.edu.cue.series_project.mapping.mappers.EpisodeMapper;
import co.edu.cue.series_project.services.EpisodeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
@Service
@AllArgsConstructor
public class EpisodeServiceImpl implements EpisodeService {
    private final EpisodeRepository episodeRepository;
    private final SeasonRepository seasonRepository;
    private final EpisodeMapper mapper;

    @Override
    public List<EpisodeDTO> getAllEpisodes() {
        return episodeRepository.findAll()
                .stream()
                .filter(Episode::getData_state)
                .map(mapper::mapFromEntity).toList();
    }

    @Override
    public List<EpisodeDTO> filterById(Long id) {
        return episodeRepository.findAll()
                .stream()
                .filter(episode -> episode.getData_state() && episode.getId() == id)
                .map(mapper::mapFromEntity)
                .toList();
    }

    @Override
    public EpisodeDTO getOneEpisode(Long id) {
        return mapper.mapFromEntity(episodeRepository.findById(id)
                .orElseThrow(() -> new EpisodeException("Episode with ID " + id + " not found"))
        );
    }

    @Override
    public EpisodeDTO createEpisode(EpisodeRequestDTO episodeRequestDTO) {
        return seasonRepository.findById(episodeRequestDTO.season_id())
                .map(season -> {
                    Episode dataModification = mapper.mapFromRequestDTO(episodeRequestDTO);
                    dataModification.setSeason(season);
                    dataModification.setData_state(true);
                    try {
                        Episode savedEpisode = episodeRepository.save(dataModification);
                        return mapper.mapFromEntity(savedEpisode);
                    } catch (Exception e) {
                        throw new EpisodeException("Error al guardar el episodio");
                    }
                })
                .orElseThrow(() -> new SeasonException("Season no encontrado"));
    }

    @Override
    public EpisodeDTO updateEpisode(EpisodeRequestDTO episodeUpdateInterfaceDTO) {
        return episodeRepository.findById(episodeUpdateInterfaceDTO.id())
                .map(existingEpisode -> seasonRepository.findById(episodeUpdateInterfaceDTO.season_id())
                        .map(season -> {
                            existingEpisode.setSeason(season);
                            existingEpisode.setData_state(true);
                            try {
                                Episode savedEpisode = episodeRepository.save(existingEpisode);
                                return mapper.mapFromEntity(savedEpisode);
                            } catch (Exception e) {
                                throw new EpisodeException("Error al guardar el episodio");
                            }
                        })
                        .orElseThrow(() -> new SeasonException("Season no encontrado"))
                )
                .orElseThrow(() -> new EpisodeException("Episode no encontrado"));
    }

    @Override
    public void deactivateEpisode(Long id) {
        Episode data=episodeRepository.findById(id).orElseThrow(() -> new EpisodeException("Episode with ID " + id + " not found"));
        data.setData_state(false);
        episodeRepository.save(data);
    }
}
