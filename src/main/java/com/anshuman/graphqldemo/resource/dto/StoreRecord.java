package com.anshuman.graphqldemo.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.anshuman.graphqldemo.model.entity.Store}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public record StoreRecord(Integer id, @NotNull AddressRecord address,
        @NotNull Instant lastUpdate) implements Serializable {
}