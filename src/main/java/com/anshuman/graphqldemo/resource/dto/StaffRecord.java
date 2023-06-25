package com.anshuman.graphqldemo.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.anshuman.graphqldemo.model.entity.Staff}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record StaffRecord(Integer id, @NotNull @Size(max = 45) String firstName,
        @NotNull @Size(max = 45) String lastName, @NotNull AddressRecord address, @Size(max = 50) String email,
        @NotNull Short storeId, @NotNull Boolean active, @NotNull @Size(max = 16) String username,
        @Size(max = 40) String password, @NotNull Instant lastUpdate, byte[] picture) implements Serializable {
}