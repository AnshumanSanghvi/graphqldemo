package com.anshuman.graphqldemo.model.mapper;

import com.anshuman.graphqldemo.model.entity.Address;
import com.anshuman.graphqldemo.resource.dto.AddressRecord;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {CityMapper.class})
public interface AddressMapper {
    Address toEntity(AddressRecord addressRecord);

    AddressRecord toDto(Address address);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Address partialUpdate(AddressRecord addressRecord, @MappingTarget Address address);
}