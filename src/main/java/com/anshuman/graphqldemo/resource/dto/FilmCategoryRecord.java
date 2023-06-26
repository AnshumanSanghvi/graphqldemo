package com.anshuman.graphqldemo.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.anshuman.graphqldemo.model.entity.FilmCategory}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public record FilmCategoryRecord(@NotNull FilmCategoryIdRecord id, FilmRecord filmRecord, CategoryRecord categoryRecord, @NotNull Instant lastUpdate) implements Serializable {
}