package com.anshuman.graphqldemo.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.anshuman.graphqldemo.model.entity.Address}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public record AddressRecord(Integer id, @NotNull @Size(max = 50) String address, @Size(max = 50) String address2,
        @NotNull @Size(max = 20) String district, @NotNull CityRecord city, @Size(max = 10) String postalCode,
        @NotNull @Size(max = 20) String phone, @NotNull Instant lastUpdate) implements Serializable {
}