package com.anshuman.graphqldemo.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "imdb_rating", schema = "public", indexes = {
        @Index(name = "imdb_imdb_id_un", columnList = "imdb_id", unique = true),
        @Index(name = "imdb_film_id_un", columnList = "film_id", unique = true)
})
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ImdbRating {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 25)
    @NotNull
    @Column(name = "imdb_id", nullable = false, length = 25)
    private String imdbId;

    @NotNull
    @Column(name = "imdb_rating", nullable = false)
    private Float rating;

    @NotNull
    @Column(name = "film_id", nullable = false)
    private Integer filmId;

    @NotNull
    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;

}