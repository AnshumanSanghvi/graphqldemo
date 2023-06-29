package com.anshuman.graphqldemo.model.mapper;

import com.anshuman.graphqldemo.model.entity.FilmCategoryId;
import com.anshuman.graphqldemo.resource.dto.FilmCategoryIdRecord;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FilmCategoryIdMapper {
    FilmCategoryId toEntity(FilmCategoryIdRecord filmCategoryIdRecord);

    FilmCategoryIdRecord toDto(FilmCategoryId filmCategoryId);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FilmCategoryId partialUpdate(FilmCategoryIdRecord filmCategoryIdRecord, @MappingTarget FilmCategoryId filmCategoryId);
}