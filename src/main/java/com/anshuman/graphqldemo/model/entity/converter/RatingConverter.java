package com.anshuman.graphqldemo.model.entity.converter;

import com.anshuman.graphqldemo.data.enums.MPAA_Rating;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RatingConverter implements AttributeConverter<MPAA_Rating, String> {
    @Override
    public String convertToDatabaseColumn(final MPAA_Rating attribute) {
        return attribute.getValue();
    }

    @Override
    public MPAA_Rating convertToEntityAttribute(final String dbData) {
        return MPAA_Rating.fromValue(dbData);
    }
}


