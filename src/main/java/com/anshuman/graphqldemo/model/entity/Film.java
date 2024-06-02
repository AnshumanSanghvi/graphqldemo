package com.anshuman.graphqldemo.model.entity;

import com.anshuman.graphqldemo.data.enums.MPAA_Rating;
import com.anshuman.graphqldemo.model.entity.converter.RatingConverter;
import io.hypersistence.utils.hibernate.type.search.PostgreSQLTSVectorType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.joining;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "film", schema = "public", indexes = {
        @Index(name = "idx_title", columnList = "title"),
        @Index(name = "idx_fk_language_id", columnList = "language_id"),
        @Index(name = "film_fulltext_idx", columnList = "fulltext")
})
public class Film {

    public Film(Integer id, String title, Integer releaseYear, Short length) {
        this.id = id;
        this.title = title;
        this.releaseYear = releaseYear;
        this.length = length;
    }

    @Id
    @Column(name = "film_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "release_year")
    private Integer releaseYear;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "language_id", nullable = false)
    @ToString.Exclude
    private Language language;

    @NotNull
    @Column(name = "rental_duration", nullable = false)
    private Short rentalDuration;

    @NotNull
    @Column(name = "rental_rate", nullable = false, precision = 4, scale = 2)
    private BigDecimal rentalRate;

    @Column(name = "length")
    private Short length;

    @NotNull
    @Column(name = "replacement_cost", nullable = false, precision = 5, scale = 2)
    private BigDecimal replacementCost;

    @NotNull
    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;

    @NotNull
    @Lob
    @Column(name = "fulltext", nullable = false)
    @Type(PostgreSQLTSVectorType.class)
    @ToString.Exclude
    private String fulltext;

    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY)
    @ToString.Exclude
    @Builder.Default
    private Set<FilmCategory> filmCategories = new LinkedHashSet<>();

    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY)
    @ToString.Exclude
    @Builder.Default
    private Set<FilmActor> filmActors = new LinkedHashSet<>();

    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY)
    @ToString.Exclude
    @Builder.Default
    private Set<Inventory> inventories = new LinkedHashSet<>();

    @Convert(converter = RatingConverter.class)
    @Column(name = "rating")
    private MPAA_Rating rating;

    @Column(name = "special_features", columnDefinition = "text")
    private String specialFeatures;

    public static String getFilmDetailed(Film f) {
        StringBuilder filmSB = new StringBuilder();
        filmSB.append("{")
                .append(" ").append("id: ").append(f.getId())
                .append(", ").append("title: ").append(f.getTitle())
                .append(", ").append("year: ").append(f.getReleaseYear())
                .append(", ").append("language: ").append(f.getLanguage().getName().trim())
                .append(", ").append("length: ").append(f.getLength()).append(" mins")
                .append(", ").append("rating: ").append(f.getRating().getValue())
                .append(", ").append("description: ").append(f.getDescription())
                .append(", ").append("categories: {").append(f.getFilmCategories().stream().map(FilmCategory::getId)
                        .map(FilmCategoryId::getCategoryId).map(String::valueOf).collect(joining(", "))).append("}")
                .append(", ").append("actors: {").append(f.getFilmActors().stream().map(FilmActor::getActor).map(a ->
                        a.getFirstName() + " " + a.getLastName()).collect(joining(", "))).append("}")
                .append("}");

        return filmSB.toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        final Film film = (Film) o;
        return getId() != null && Objects.equals(getId(), film.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}