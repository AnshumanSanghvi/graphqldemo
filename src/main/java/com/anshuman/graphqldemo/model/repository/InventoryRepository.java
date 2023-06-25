package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.Inventory;
import org.springframework.data.repository.ListCrudRepository;

public interface InventoryRepository extends ListCrudRepository<Inventory, Integer> {
}