package com.anshuman.graphqldemo.model.repository.projection;

public interface ICityProjection {
    Integer getId();

    String getCity();

    Integer getCountryId();

    public static CityProjection toPojo(ICityProjection projection) {
        return new CityProjection(projection.getId(), projection.getCity(), projection.getCountryId());
    }
}
