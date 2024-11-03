package co.edu.cue.series_project.services;

import co.edu.cue.series_project.mapping.dtos.*;

import java.util.List;

public interface SeriesService {
    List<SeriesDTO> getAllSeries();
    SeriesDTO getOneSeries(Long id);
    SeriesDTO createSeries(SeriesRequestDTO seriesRequestDTO);

    void deactivateSeries(Long id);
}
