package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.Category;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CategoryRepository extends ListCrudRepository<Category, Integer> {
}