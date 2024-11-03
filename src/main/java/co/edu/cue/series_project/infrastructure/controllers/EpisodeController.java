package co.edu.cue.series_project.infrastructure.controllers;



import co.edu.cue.series_project.infrastructure.utils.ResponseMessageUtil;
import co.edu.cue.series_project.mapping.dtos.EpisodeDTO;
import co.edu.cue.series_project.mapping.dtos.EpisodeRequestDTO;
import co.edu.cue.series_project.services.EpisodeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/episode")
@CrossOrigin(origins = "http://localhost:63342")
public class EpisodeController {
    private final EpisodeService service;

    @GetMapping("/get-all")
    public ResponseEntity<Map<String, List<EpisodeDTO>>> getAllEpisodes(){
        List<EpisodeDTO> episodes = service.getAllEpisodes();
        Map<String, List<EpisodeDTO>> response = ResponseMessageUtil.responseMessage("episodes", episodes);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, EpisodeDTO>> createEpisode(@RequestBody
                                                                 @Valid
                                                                 EpisodeRequestDTO createDTO) throws IOException {
        EpisodeDTO createdEpisode = service.createEpisode(createDTO);
        Map<String, EpisodeDTO> response = ResponseMessageUtil.responseMessage("episode", createdEpisode);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Map<String, EpisodeDTO>> getEpisodeById(@PathVariable
                                                                  Long id) {
        EpisodeDTO episodeDTO = service.getOneEpisode(id);
        Map<String, EpisodeDTO> response = ResponseMessageUtil.responseMessage("episode", episodeDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/filter/{id}")
    public ResponseEntity<Map<String, List<EpisodeDTO>>> getFilterById(@PathVariable
                                                                  Long id) {
        List<EpisodeDTO> episodeDTO = service.filterById(id);
        Map<String, List<EpisodeDTO>> response = ResponseMessageUtil.responseMessage("episode", episodeDTO);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/update")
    public ResponseEntity<Map<String, EpisodeDTO>> updateEpisode(@RequestBody
                                                                 @Valid
                                                                     EpisodeRequestDTO episodeUpdateDTO){
        EpisodeDTO episodeDTO = service.updateEpisode(episodeUpdateDTO);
        Map<String, EpisodeDTO> response = ResponseMessageUtil.responseMessage("episode", episodeDTO);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteEpisode(@PathVariable Long id){
        service.deactivateEpisode(id);
        Map<String, String> response = ResponseMessageUtil.responseMessage("message", "episode deleted");
        return ResponseEntity.ok(response);
    }
}
