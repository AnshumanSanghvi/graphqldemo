package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.Rental;
import org.springframework.data.repository.ListCrudRepository;

public interface RentalRepository extends ListCrudRepository<Rental, Integer> {
}