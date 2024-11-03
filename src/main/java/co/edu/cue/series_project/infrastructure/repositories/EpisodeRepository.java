package co.edu.cue.series_project.infrastructure.repositories;

import co.edu.cue.series_project.domain.entities.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode,Long> {
}
