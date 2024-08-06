package com.anshuman.graphqldemo.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * DTO for {@link com.anshuman.graphqldemo.model.entity.Staff}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public record StaffRecord(Integer id, @NotNull @Size(max = 45) String firstName,
        @NotNull @Size(max = 45) String lastName, @NotNull AddressRecord address, @Size(max = 50) String email,
        @NotNull Short storeId, @NotNull Boolean active, @NotNull @Size(max = 16) String username,
        @Size(max = 40) String password, @NotNull Instant lastUpdate, byte[] picture) implements Serializable {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StaffRecord that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "StaffRecord{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address=" + address +
                ", email='" + email + '\'' +
                ", storeId=" + storeId +
                ", active=" + active +
                ", username='" + username + '\'' +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}