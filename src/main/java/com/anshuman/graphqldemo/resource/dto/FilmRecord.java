package com.anshuman.graphqldemo.resource.dto;

import com.anshuman.graphqldemo.data.enums.MPAA_Rating;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

/**
 * DTO for {@link com.anshuman.graphqldemo.model.entity.Film}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public record FilmRecord(Integer id, @NotNull @Size(max = 255) String title, String description, Integer releaseYear,
                         Integer language, @NotNull Short rentalDuration, @NotNull BigDecimal rentalRate,
                         Short length,
                         @NotNull BigDecimal replacementCost, @NotNull Instant lastUpdate, @NotNull String fulltext,
                         Set<FilmCategoryRecord> filmCategories, Set<FilmActorRecord> filmActors,
                         Set<InventoryRecord> inventories, MPAA_Rating rating,
                         String specialFeatures) implements Serializable {
}