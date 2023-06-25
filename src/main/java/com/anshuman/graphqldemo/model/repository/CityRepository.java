package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.City;
import org.springframework.data.repository.ListCrudRepository;

public interface CityRepository extends ListCrudRepository<City, Integer> {
}