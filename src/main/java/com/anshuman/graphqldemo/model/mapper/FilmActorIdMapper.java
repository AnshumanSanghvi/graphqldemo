package com.anshuman.graphqldemo.model.mapper;

import com.anshuman.graphqldemo.model.entity.FilmActorId;
import com.anshuman.graphqldemo.resource.dto.FilmActorIdRecord;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FilmActorIdMapper {
    FilmActorId toEntity(FilmActorIdRecord filmActorIdRecord);

    FilmActorIdRecord toDto(FilmActorId filmActorId);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FilmActorId partialUpdate(FilmActorIdRecord filmActorIdRecord, @MappingTarget FilmActorId filmActorId);
}