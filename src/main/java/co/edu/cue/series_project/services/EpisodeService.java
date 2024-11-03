package co.edu.cue.series_project.services;

import co.edu.cue.series_project.mapping.dtos.EpisodeDTO;
import co.edu.cue.series_project.mapping.dtos.EpisodeRequestDTO;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface EpisodeService {
    List<EpisodeDTO> getAllEpisodes();
    List<EpisodeDTO> filterById(Long id);

    EpisodeDTO getOneEpisode(Long id);
    EpisodeDTO createEpisode(EpisodeRequestDTO createEpisodeDTO);
    EpisodeDTO updateEpisode(EpisodeRequestDTO episodeUpdateInterfaceDTO);

    void deactivateEpisode(Long id);
}
