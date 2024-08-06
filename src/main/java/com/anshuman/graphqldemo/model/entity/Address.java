package com.anshuman.graphqldemo.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Table(name = "address", schema = "public", indexes = {
        @Index(name = "idx_fk_city_id", columnList = "city_id")
})
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Address {
    @Id
    @Column(name = "address_id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "address", nullable = false, length = 50)
    private String addressLine;

    @Size(max = 50)
    @Column(name = "address2", length = 50)
    private String addressLine2;

    @Size(max = 20)
    @NotNull
    @Column(name = "district", nullable = false, length = 20)
    private String district;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    @ToString.Exclude
    private City city;

    @Size(max = 10)
    @Column(name = "postal_code", length = 10)
    private String postalCode;

    @Size(max = 20)
    @NotNull
    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @NotNull
    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        final Address addressObject = (Address) o;
        return getId() != null && Objects.equals(getId(), addressObject.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Address(final Integer id, @NotNull final String addressLine, final String addressLine2, @NotNull final String district, final String postalCode, @NotNull final String phone) {
        this.id = id;
        this.addressLine = addressLine;
        this.addressLine2 = addressLine2;
        this.district = district;
        this.postalCode = postalCode;
        this.phone = phone;
    }
}