package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.Customer;
import org.springframework.data.repository.ListCrudRepository;

public interface CustomerRepository extends ListCrudRepository<Customer, Integer> {
}