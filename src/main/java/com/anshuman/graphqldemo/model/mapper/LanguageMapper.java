package com.anshuman.graphqldemo.model.mapper;

import com.anshuman.graphqldemo.model.entity.Language;
import com.anshuman.graphqldemo.resource.dto.LanguageRecord;
import org.mapstruct.*;

import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LanguageMapper {

    Language toEntity(LanguageRecord languageRecord);

    LanguageRecord toDto(Language language);

    Set<LanguageRecord> toDtos(Set<Language> languages);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Language partialUpdate(LanguageRecord languageRecord, @MappingTarget Language language);
}