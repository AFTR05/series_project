package co.edu.cue.series_project.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
@Entity
@Table(name = "season")
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "serie_id")
    private Serie serie;
    private Integer seasonNumber;
    private Date startDate;
    private Boolean data_state;
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY,  orphanRemoval = true)
    @JoinTable(name = "season_episode")
    @JsonIgnore
    private List<Episode> episodes;
}
