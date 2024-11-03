package co.edu.cue.series_project.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Duration;
import java.util.Date;

@Data
@Entity
@Table(name = "episode")
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Duration duration;
    private Date startDate;
    private Boolean data_state;
    @ManyToOne()
    @JoinColumn(name = "season_id")
    private Season season;
}
