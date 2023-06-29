package com.anshuman.graphqldemo.model.mapper;

import com.anshuman.graphqldemo.model.entity.ImdbRating;
import com.anshuman.graphqldemo.resource.dto.ImdbRatingRecord;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ImdbRatingMapper {
    ImdbRating toEntity(ImdbRatingRecord imdbRatingRecord);

    ImdbRatingRecord toDto(ImdbRating imdbRating);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ImdbRating partialUpdate(ImdbRatingRecord imdbRatingRecord, @MappingTarget ImdbRating imdbRating);
}