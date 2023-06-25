package com.anshuman.graphqldemo.model.mapper;

import com.anshuman.graphqldemo.model.entity.Rental;
import com.anshuman.graphqldemo.resource.dto.RentalRecord;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {InventoryMapper.class, CustomerMapper.class, StaffMapper.class})
public interface RentalMapper {
    Rental toEntity(RentalRecord rentalRecord);

    RentalRecord toDto(Rental rental);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Rental partialUpdate(RentalRecord rentalRecord, @MappingTarget Rental rental);
}