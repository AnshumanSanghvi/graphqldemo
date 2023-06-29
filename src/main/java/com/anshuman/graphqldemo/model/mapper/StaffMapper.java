package com.anshuman.graphqldemo.model.mapper;

import com.anshuman.graphqldemo.model.entity.Staff;
import com.anshuman.graphqldemo.resource.dto.StaffRecord;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {AddressMapper.class})
public interface StaffMapper {
    Staff toEntity(StaffRecord staffRecord);

    StaffRecord toDto(Staff staff);

    List<StaffRecord> toDtoList(List<Staff> staff);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Staff partialUpdate(StaffRecord staffRecord, @MappingTarget Staff staff);
}