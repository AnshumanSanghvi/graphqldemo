package com.anshuman.graphqldemo.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.anshuman.graphqldemo.model.entity.Actor}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public record ActorRecord(Integer id, @NotNull @Size(max = 45) String firstName,
        @NotNull @Size(max = 45) String lastName, @NotNull Instant lastUpdate) implements Serializable {
}