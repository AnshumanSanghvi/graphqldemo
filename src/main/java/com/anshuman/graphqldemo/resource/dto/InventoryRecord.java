package com.anshuman.graphqldemo.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.anshuman.graphqldemo.model.entity.Inventory}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record InventoryRecord(Integer id, @NotNull Short storeId, @NotNull Instant lastUpdate) implements Serializable {
}