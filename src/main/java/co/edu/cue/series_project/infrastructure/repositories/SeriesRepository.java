package co.edu.cue.series_project.infrastructure.repositories;

import co.edu.cue.series_project.domain.entities.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeriesRepository extends JpaRepository<Serie,Long> {
    Optional<Serie> findById(Long id);
}
