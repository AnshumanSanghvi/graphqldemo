package com.anshuman.graphqldemo.model.mapper;

import com.anshuman.graphqldemo.model.entity.Country;
import com.anshuman.graphqldemo.resource.dto.CountryRecord;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CountryMapper {
    Country toEntity(CountryRecord countryRecord);

    CountryRecord toDto(Country country);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Country partialUpdate(CountryRecord countryRecord, @MappingTarget Country country);
}