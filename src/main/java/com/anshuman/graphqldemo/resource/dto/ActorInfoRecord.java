package com.anshuman.graphqldemo.resource.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * DTO for {@link com.anshuman.graphqldemo.model.entity.view.ActorInfo}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public record ActorInfoRecord(Integer actorId, @Size(max = 45) String firstName, @Size(max = 45) String lastName,
        String filmInfo) implements Serializable {
}