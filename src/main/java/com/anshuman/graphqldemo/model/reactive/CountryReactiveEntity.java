package com.anshuman.graphqldemo.model.reactive;

import com.anshuman.graphqldemo.annotation.Reactive;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Reactive
@Table(name = "country", schema = "public")
public class CountryReactiveEntity {
    @Id
    @Column(name = "country_id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @NotNull
    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        final CountryReactiveEntity countryReactiveEntity = (CountryReactiveEntity) o;
        return getId() != null && Objects.equals(getId(), countryReactiveEntity.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}