package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.Set;

@RepositoryRestResource
public interface CategoryRepository extends ListCrudRepository<Category, Integer> {

    @Query(value = " SELECT DISTINCT C " +
            " FROM Category C " +
            " WHERE C.id IN (:ids) ")
    Set<Category> findDistinctByIdIn(Collection<Short> ids);
}