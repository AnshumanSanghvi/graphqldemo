package com.anshuman.graphqldemo.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.anshuman.graphqldemo.model.entity.Rental}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record RentalRecord(Integer id, @NotNull Instant rentalDate, @NotNull InventoryRecord inventory,
        @NotNull CustomerRecord customer, Instant returnDate, @NotNull StaffRecord staff,
        @NotNull Instant lastUpdate) implements Serializable {
}