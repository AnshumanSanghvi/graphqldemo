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
@Table(name = "rotten_tomatoes_rating", schema = "public", indexes = {
        @Index(name = "rotten_tomatoes_rotten_tomatoes_id_un", columnList = "rotten_tomatoes_id", unique = true),
        @Index(name = "rotten_tomatoes_film_id_un", columnList = "film_id", unique = true)
})
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RottenTomatoesRating {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 25)
    @NotNull
    @Column(name = "rotten_tomatoes_id", nullable = false, length = 25)
    private String rottenTomatoesId;

    @NotNull
    @Column(name = "rotten_tomatoes_rating", nullable = false)
    private Float rottenTomatoesRating;

    @NotNull
    @Column(name = "film_id", nullable = false)
    private Integer filmId;

    @NotNull
    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;

}