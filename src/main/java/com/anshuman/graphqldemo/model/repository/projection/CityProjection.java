package com.anshuman.graphqldemo.model.repository.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CityProjection implements ICityProjection {
    private Integer id;
    private String name;
    private Integer countryId;
}


