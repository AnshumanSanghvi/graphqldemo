package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.Staff;
import org.springframework.data.repository.ListCrudRepository;

public interface StaffRepository extends ListCrudRepository<Staff, Integer> {
}