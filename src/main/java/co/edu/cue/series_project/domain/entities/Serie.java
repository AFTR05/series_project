package co.edu.cue.series_project.domain.entities;

import co.edu.cue.series_project.domain.enums.State;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "serie")
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Date startDate;
    private Boolean data_state;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinTable(name = "serie_seasons")
    @JsonIgnore
    private List<Season> seasons;

    private Double qualification;

    @Enumerated(EnumType.STRING)
    private State state;
}
