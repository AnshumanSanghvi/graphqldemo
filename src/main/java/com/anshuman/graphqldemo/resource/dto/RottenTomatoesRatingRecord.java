package com.anshuman.graphqldemo.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.anshuman.graphqldemo.model.entity.RottenTomatoesRating}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public record RottenTomatoesRatingRecord(Integer id, @NotNull @Size(max = 25) String rottenTomatoesId,
                                         @NotNull Float rating, @NotNull Integer filmId,
                                         @NotNull Instant lastUpdate) implements Serializable {
}