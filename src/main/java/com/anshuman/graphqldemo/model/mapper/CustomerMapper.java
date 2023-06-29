package com.anshuman.graphqldemo.model.mapper;

import com.anshuman.graphqldemo.model.entity.Customer;
import com.anshuman.graphqldemo.resource.dto.CustomerRecord;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {
    Customer toEntity(CustomerRecord customerRecord);

    CustomerRecord toDto(Customer customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Customer partialUpdate(CustomerRecord customerRecord, @MappingTarget Customer customer);
}