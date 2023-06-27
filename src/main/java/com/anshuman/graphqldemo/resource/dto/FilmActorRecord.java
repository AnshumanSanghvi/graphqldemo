package com.anshuman.graphqldemo.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.anshuman.graphqldemo.model.entity.FilmActor}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public record FilmActorRecord(@NotNull FilmActorIdRecord id, ActorRecord actor,
        FilmRecord film, @NotNull Instant lastUpdate) implements Serializable {
}