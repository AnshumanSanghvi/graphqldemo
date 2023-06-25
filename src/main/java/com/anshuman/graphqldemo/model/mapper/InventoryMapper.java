package com.anshuman.graphqldemo.model.mapper;

import com.anshuman.graphqldemo.model.entity.Inventory;
import com.anshuman.graphqldemo.resource.dto.InventoryRecord;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface InventoryMapper {
    Inventory toEntity(InventoryRecord inventoryRecord);

    InventoryRecord toDto(Inventory inventory);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Inventory partialUpdate(InventoryRecord inventoryRecord, @MappingTarget Inventory inventory);
}