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
@Table(name = "rental", schema = "public", indexes = {
        @Index(name = "idx_unq_rental_rental_date_inventory_id_customer_id",
                columnList = "rental_date, inventory_id, customer_id", unique = true),
        @Index(name = "idx_fk_inventory_id", columnList = "inventory_id")
})
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Rental {
    @Id
    @Column(name = "rental_id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "rental_date", nullable = false)
    private Instant rentalDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id", nullable = false)
    @ToString.Exclude
    private Inventory inventory;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @ToString.Exclude
    private Customer customer;

    @Column(name = "return_date")
    private Instant returnDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", nullable = false)
    @ToString.Exclude
    private Staff staff;

    @NotNull
    @Column(name = "last_update", nullable = false)
    private Instant lastUpdate;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        final Rental rental = (Rental) o;
        return getId() != null && Objects.equals(getId(), rental.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}