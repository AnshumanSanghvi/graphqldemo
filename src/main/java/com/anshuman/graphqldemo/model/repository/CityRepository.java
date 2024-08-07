package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.City;
import com.anshuman.graphqldemo.model.repository.projection.ICityProjection;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CityRepository extends ListCrudRepository<City, Integer> {


    @Query(nativeQuery = true, value = "SELECT " +
            " c.city_id as id, " +
            " c.city as name, " +
            " c.country_id AS countryId " +
            " FROM public.city c " +
            " WHERE c.city_id = :cityId ")
    ICityProjection gqlFindById(@NotNull Integer cityId);
}