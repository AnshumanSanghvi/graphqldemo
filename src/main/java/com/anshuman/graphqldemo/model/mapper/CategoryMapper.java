package com.anshuman.graphqldemo.model.mapper;

import com.anshuman.graphqldemo.model.entity.Category;
import com.anshuman.graphqldemo.resource.dto.CategoryRecord;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    Category toEntity(CategoryRecord categoryRecord);

    CategoryRecord toDto(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Category partialUpdate(CategoryRecord categoryRecord, @MappingTarget Category category);
}