package co.edu.cue.series_project.infrastructure.controllers;

import co.edu.cue.series_project.infrastructure.utils.ResponseMessageUtil;
import co.edu.cue.series_project.mapping.dtos.*;
import co.edu.cue.series_project.services.SeasonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/season")
@CrossOrigin(origins = "http://localhost:63342")
public class SeasonController {
    private final SeasonService service;

    @GetMapping("/get-all")
    public ResponseEntity<Map<String, List<SeasonDTO>>> getAllSeasons(){
        List<SeasonDTO> seasons = service.getAllSeason();
        Map<String, List<SeasonDTO>> response = ResponseMessageUtil.responseMessage("seasons", seasons);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, SeasonDTO>> createSeason(@RequestBody
                                                                 @Valid
                                                                SeasonRequestDTO createSeasonDTO){
        SeasonDTO createdSeason = service.createSeason(createSeasonDTO);
        Map<String, SeasonDTO> response = ResponseMessageUtil.responseMessage("season", createdSeason);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Map<String, SeasonDTO>> getSeasonById(@PathVariable
                                                                  Long id) {
        SeasonDTO seasonDTO = service.getOneSeason(id);
        Map<String, SeasonDTO> response = ResponseMessageUtil.responseMessage("season", seasonDTO);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/update")
    public ResponseEntity<Map<String, SeasonDTO>> updateSeason(@RequestBody
                                                                 @Valid
                                                               SeasonUpdateDTO seasonUpdateDTO){
        SeasonDTO seasonDTO = service.updateSeason(seasonUpdateDTO);
        Map<String, SeasonDTO> response = ResponseMessageUtil.responseMessage("season", seasonDTO);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteSeason(@PathVariable Long id){
        service.deactivateSeason(id);
        Map<String, String> response = ResponseMessageUtil.responseMessage("message", "season deleted");
        return ResponseEntity.ok(response);
    }
}
