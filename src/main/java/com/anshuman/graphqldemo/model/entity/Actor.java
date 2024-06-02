package com.anshuman.graphqldemo.model.entity;

//import io.leangen.graphql.annotations.GraphQLArgument;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "actor", schema = "public", indexes = {
        @Index(name = "idx_actor_last_name", columnList = "last_name")
})
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id", nullable = false)
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Size(max = 45)
    @NotNull
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @NotNull
    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;

    @OneToMany(mappedBy = "actor", fetch = FetchType.LAZY)
    @ToString.Exclude
    @Builder.Default
    private Set<FilmActor> filmActors = new LinkedHashSet<>();

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        final Actor actor = (Actor) o;
        return getId() != null && Objects.equals(getId(), actor.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}