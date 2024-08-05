package com.anshuman.graphqldemo.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.anshuman.graphqldemo.model.entity.Country}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public record CountryRecord(Integer id, @NotNull @Size(max = 50) String name,
        @NotNull Instant lastUpdate) implements Serializable {
}