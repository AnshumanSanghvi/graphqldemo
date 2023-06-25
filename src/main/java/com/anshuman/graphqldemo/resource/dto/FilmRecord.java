package com.anshuman.graphqldemo.resource.dto;

import com.anshuman.graphqldemo.data.enums.MPAA_Rating;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for {@link com.anshuman.graphqldemo.model.entity.Film}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record FilmRecord(Integer id, @NotNull @Size(max = 255) String title, String description, Integer releaseYear,
        @NotNull Short rentalDuration, @NotNull BigDecimal rentalRate, Short length,
        @NotNull BigDecimal replacementCost, @NotNull Instant lastUpdate, @NotNull String fulltext, MPAA_Rating rating,
        String specialFeatures) implements Serializable {
}