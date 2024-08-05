package com.anshuman.graphqldemo.model.repository.projection;

public interface ICityProjection {
    Integer getId();

    String getCity();

    Integer getCountryId();

    static CityProjection toPojo(ICityProjection projection) {
        return new CityProjection(projection.getId(), projection.getCity(), projection.getCountryId());
    }
}
