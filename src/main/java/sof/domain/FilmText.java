package sof.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.Objects;

@Entity
@Table(name = "film_text", schema = "movie")
@Getter
@Setter
public class FilmText {

    @Id
    @Column(name = "film_id", nullable = false)
    private Short filmId;

    @OneToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "text")
    @Type(type = "text")
    private String description;

}
