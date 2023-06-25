package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.Country;
import org.springframework.data.repository.ListCrudRepository;

public interface CountryRepository extends ListCrudRepository<Country, Integer> {
}