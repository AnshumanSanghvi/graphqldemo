package com.anshuman.graphqldemo.model.mapper;

import com.anshuman.graphqldemo.model.entity.Store;
import com.anshuman.graphqldemo.resource.dto.StoreRecord;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {AddressMapper.class})
public interface StoreMapper {
    Store toEntity(StoreRecord storeRecord);

    StoreRecord toDto(Store store);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Store partialUpdate(StoreRecord storeRecord, @MappingTarget Store store);
}