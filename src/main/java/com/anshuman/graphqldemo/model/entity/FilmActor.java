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
@Table(name = "film_actor", schema = "public", indexes = {
        @Index(name = "idx_fk_film_id", columnList = "film_id")
})
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FilmActor {
    @EmbeddedId
    private FilmActorId id;

    @MapsId("filmId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id", nullable = false)
    @ToString.Exclude
    private Film film;

    @NotNull
    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;

    public Short getFilmId() {
        return this.id.getFilmId();
    }

    public void setFilmId(Short filmId) {
        FilmActorId filmActorId = (this.getId()) == null ? new FilmActorId() : this.getId();
        filmActorId.setFilmId(filmId);
    }

    public Short getActorId() {
        return this.id.getActorId();
    }

    public void setActorId(Short actorId) {
        FilmActorId filmActorId = (this.getId()) == null ? new FilmActorId() : this.getId();
        filmActorId.setActorId(actorId);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        final FilmActor filmActor = (FilmActor) o;
        return getId() != null && Objects.equals(getId(), filmActor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}