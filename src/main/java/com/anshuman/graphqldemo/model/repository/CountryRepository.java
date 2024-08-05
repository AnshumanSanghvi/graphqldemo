package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.Country;
import jakarta.annotation.Nonnull;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.scheduling.annotation.Async;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@RepositoryRestResource
public interface CountryRepository extends ListCrudRepository<Country, Integer> {

    @Override
    void deleteById(@Nonnull Integer countryId);

    @Async("APIThreadExecutor")
    @Query(" SELECT c " +
            " FROM Country c " +
            " WHERE c.id = :countryId ")
    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true") })
    CompletableFuture<Country> gqlFindById(Integer countryId);

    @Async("APIThreadExecutor")
    @Query(" SELECT DISTINCT c " +
            " FROM Country c " +
            " WHERE c.id IN (:countryIds) ")
    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true") })
    CompletableFuture<Set<Country>> gqlFindByIds(Set<Integer> countryIds);
}