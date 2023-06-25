package com.anshuman.graphqldemo.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.anshuman.graphqldemo.model.entity.Language}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record LanguageRecord(Integer id, @NotNull @Size(max = 20) String name,
        @NotNull Instant lastUpdate) implements Serializable {
}