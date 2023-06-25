package com.anshuman.graphqldemo.model.mapper;

import com.anshuman.graphqldemo.model.entity.FilmActor;
import com.anshuman.graphqldemo.resource.dto.FilmActorRecord;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {FilmActorIdMapper.class})
public interface FilmActorMapper {
    FilmActor toEntity(FilmActorRecord filmActorRecord);

    FilmActorRecord toDto(FilmActor filmActor);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FilmActor partialUpdate(FilmActorRecord filmActorRecord, @MappingTarget FilmActor filmActor);
}