package com.anshuman.graphqldemo.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.anshuman.graphqldemo.model.entity.Country}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record CountryRecord(Integer id, @NotNull @Size(max = 50) String country,
        @NotNull Instant lastUpdate) implements Serializable {
}