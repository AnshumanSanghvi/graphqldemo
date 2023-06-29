package com.anshuman.graphqldemo.model.mapper;

import com.anshuman.graphqldemo.model.entity.City;
import com.anshuman.graphqldemo.resource.dto.CityRecord;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {CountryMapper.class})
public interface CityMapper {
    City toEntity(CityRecord cityRecord);

    CityRecord toDto(City city);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    City partialUpdate(CityRecord cityRecord, @MappingTarget City city);
}