package com.anshuman.graphqldemo.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * DTO for {@link com.anshuman.graphqldemo.model.entity.Payment}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public record PaymentRecord(Integer id, @NotNull CustomerRecord customer, @NotNull StaffRecord staff,
        @NotNull RentalRecord rental, @NotNull BigDecimal amount,
        @NotNull Instant paymentDate) implements Serializable {
}