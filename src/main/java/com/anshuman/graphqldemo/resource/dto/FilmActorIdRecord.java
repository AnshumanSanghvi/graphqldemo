package com.anshuman.graphqldemo.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * DTO for {@link com.anshuman.graphqldemo.model.entity.FilmActorId}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public record FilmActorIdRecord(@NotNull Short actorId, @NotNull Short filmId) implements Serializable {
}