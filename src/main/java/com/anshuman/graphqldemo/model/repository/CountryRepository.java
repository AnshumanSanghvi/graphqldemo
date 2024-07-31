package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.Country;
import jakarta.persistence.QueryHint;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CountryRepository extends ListCrudRepository<Country, Integer> {
    @Override
    void deleteById(@NotNull Integer countryId);

    @Query("SELECT c FROM Country c WHERE c.id = :countryId")
    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true") })
    Country gqlFindById(Integer countryId);
}