package com.anshuman.graphqldemo.model.mapper;

import com.anshuman.graphqldemo.model.entity.RottenTomatoesRating;
import com.anshuman.graphqldemo.resource.dto.RottenTomatoesRatingRecord;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RottenTomatoesRatingMapper {
    RottenTomatoesRating toEntity(RottenTomatoesRatingRecord rottenTomatoesRatingRecord);

    RottenTomatoesRatingRecord toDto(RottenTomatoesRating rottenTomatoesRating);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    RottenTomatoesRating partialUpdate(RottenTomatoesRatingRecord rottenTomatoesRatingRecord, @MappingTarget RottenTomatoesRating rottenTomatoesRating);
}