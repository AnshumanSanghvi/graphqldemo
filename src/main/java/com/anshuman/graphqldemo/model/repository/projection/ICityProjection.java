package com.anshuman.graphqldemo.model.repository.projection;

public interface ICityProjection {
    Integer getId();

    String getName();

    Integer getCountryId();

    static CityProjection toPojo(ICityProjection projection) {
        return new CityProjection(projection.getId(), projection.getName(), projection.getCountryId());
    }
}
