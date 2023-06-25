package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.Store;
import org.springframework.data.repository.ListCrudRepository;

public interface StoreRepository extends ListCrudRepository<Store, Integer> {
}