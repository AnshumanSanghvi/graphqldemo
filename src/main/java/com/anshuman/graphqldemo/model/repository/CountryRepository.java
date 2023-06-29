package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.Country;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CountryRepository extends ListCrudRepository<Country, Integer> {
    @Override
    void deleteById(@NotNull Integer countryId);
}