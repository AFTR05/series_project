package co.edu.cue.series_project.infrastructure.repositories;

import co.edu.cue.series_project.domain.entities.Season;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonRepository extends JpaRepository<Season,Long> {
}
