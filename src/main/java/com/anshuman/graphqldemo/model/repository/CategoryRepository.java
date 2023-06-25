package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.Category;
import org.springframework.data.repository.ListCrudRepository;

public interface CategoryRepository extends ListCrudRepository<Category, Integer> {
}