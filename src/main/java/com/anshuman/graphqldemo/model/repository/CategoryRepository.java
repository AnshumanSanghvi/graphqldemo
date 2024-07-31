package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.Category;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.scheduling.annotation.Async;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@RepositoryRestResource
public interface CategoryRepository extends ListCrudRepository<Category, Integer> {
    @Async("APIThreadExecutor")
    @Query(value = " SELECT DISTINCT C " +
            " FROM Category C " +
            " WHERE C.id IN (:ids) ")
    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true") })
    CompletableFuture<Set<Category>> findDistinctCategoryByIdIn(Collection<Short> ids);
}