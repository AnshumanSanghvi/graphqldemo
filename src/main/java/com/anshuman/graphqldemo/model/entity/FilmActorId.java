package com.anshuman.graphqldemo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class FilmActorId implements Serializable {
    @Serial
    private static final long serialVersionUID = 4843171897376355324L;

    @NotNull
    @Column(name = "actor_id", nullable = false)
    private Short actorId;

    @NotNull
    @Column(name = "film_id", nullable = false)
    private Short filmId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FilmActorId entity = (FilmActorId) o;
        return Objects.equals(this.actorId, entity.actorId) &&
                Objects.equals(this.filmId, entity.filmId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorId, filmId);
    }

    @Override
    public String toString() {
        return this.getFilmId() + "-" + this.getActorId();
    }


}