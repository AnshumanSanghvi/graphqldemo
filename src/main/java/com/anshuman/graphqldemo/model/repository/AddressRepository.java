package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.Address;
import org.springframework.data.repository.ListCrudRepository;

public interface AddressRepository extends ListCrudRepository<Address, Integer> {
}