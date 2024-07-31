package com.anshuman.graphqldemo.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.time.Instant;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "film_category", schema = "public")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FilmCategory {
    @EmbeddedId
    private FilmCategoryId id;

//    @MapsId("filmId")
//    @ManyToOne(fetch = FetchType.LAZY, optional = true)
//    @JoinColumn(name = "film_id", nullable = false)
//    @ToString.Exclude
//    @Column(name = "film_id")
//    private Integer film;

    @NotNull
    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;

    @MapsId("categoryId")
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "category_id", nullable = false)
    @ToString.Exclude
    private Category category;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        final FilmCategory that = (FilmCategory) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}