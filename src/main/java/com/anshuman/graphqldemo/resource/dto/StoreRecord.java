package com.anshuman.graphqldemo.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.anshuman.graphqldemo.model.entity.Store}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record StoreRecord(Integer id, @NotNull AddressRecord address,
        @NotNull Instant lastUpdate) implements Serializable {
}