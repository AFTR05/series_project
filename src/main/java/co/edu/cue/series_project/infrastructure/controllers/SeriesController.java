package co.edu.cue.series_project.infrastructure.controllers;

import co.edu.cue.series_project.infrastructure.utils.ResponseMessageUtil;
import co.edu.cue.series_project.mapping.dtos.*;
import co.edu.cue.series_project.services.SeriesService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/serie")
@CrossOrigin(origins = "http://localhost:63342")
public class SeriesController {

    private final SeriesService service;

    @GetMapping("/get-all")
    public ResponseEntity<Map<String, List<SeriesDTO>>> getAllSeries(){
        List<SeriesDTO> series = service.getAllSeries();
        Map<String, List<SeriesDTO>> response = ResponseMessageUtil.responseMessage("series", series);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, SeriesDTO>> createSeries(@RequestBody
                                                               @Valid
                                                               SeriesRequestDTO createSeriesDTO){
        SeriesDTO createdSeries = service.createSeries(createSeriesDTO);
        Map<String, SeriesDTO> response = ResponseMessageUtil.responseMessage("serie", createdSeries);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Map<String, SeriesDTO>> getSeriesById(@PathVariable
                                                                Long id) {
        SeriesDTO seriesDTO = service.getOneSeries(id);
        Map<String, SeriesDTO> response = ResponseMessageUtil.responseMessage("serie", seriesDTO);
        return ResponseEntity.ok(response);
    }


    @PatchMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteSeries(@PathVariable Long id){
        service.deactivateSeries(id);
        Map<String, String> response = ResponseMessageUtil.responseMessage("message", "serie deleted");
        return ResponseEntity.ok(response);
    }
}
