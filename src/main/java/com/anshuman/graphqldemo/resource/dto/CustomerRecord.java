package com.anshuman.graphqldemo.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

/**
 * DTO for {@link com.anshuman.graphqldemo.model.entity.Customer}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public record CustomerRecord(Integer id, @NotNull Short storeId, @NotNull @Size(max = 45) String firstName,
        @NotNull @Size(max = 45) String lastName, @Size(max = 50) String email, @NotNull Boolean activebool,
        @NotNull LocalDate createDate, Instant lastUpdate, Integer active) implements Serializable {
}