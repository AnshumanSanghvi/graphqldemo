package com.anshuman.graphqldemo.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * DTO for {@link com.anshuman.graphqldemo.model.entity.FilmCategoryId}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public record FilmCategoryIdRecord(@NotNull Short filmId, @NotNull Short categoryId) implements Serializable {
}