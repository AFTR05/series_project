package co.edu.cue.series_project.services;

import co.edu.cue.series_project.mapping.dtos.*;

import java.util.List;

public interface SeasonService {
    List<SeasonDTO> getAllSeason();
    SeasonDTO getOneSeason(Long id);
    SeasonDTO createSeason(SeasonRequestDTO seasonRequestDTO);
    SeasonDTO updateSeason(SeasonUpdateDTO seasonUpdateInterfaceDTO);

    void deactivateSeason(Long id);
}
