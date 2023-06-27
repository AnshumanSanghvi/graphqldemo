package com.anshuman.graphqldemo.model.mapper;

import com.anshuman.graphqldemo.model.entity.FilmCategory;
import com.anshuman.graphqldemo.resource.dto.FilmCategoryRecord;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {FilmCategoryIdMapper.class, CategoryMapper.class})
public interface FilmCategoryMapper {
    FilmCategory toEntity(FilmCategoryRecord filmCategoryRecord);

    @Mapping(ignore = true, target = "film")
    FilmCategoryRecord toDto(FilmCategory filmCategory);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FilmCategory partialUpdate(FilmCategoryRecord filmCategoryRecord, @MappingTarget FilmCategory filmCategory);
}