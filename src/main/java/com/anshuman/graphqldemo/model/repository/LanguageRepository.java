package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.Language;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.scheduling.annotation.Async;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@RepositoryRestResource
public interface LanguageRepository extends ListCrudRepository<Language, Integer> {

    @Query(value = " SELECT L " +
            " FROM Language L " +
            " INNER JOIN FETCH L.films F" +
            " WHERE F.id = :filmId")
    Language getLanguageByFilmId(Integer filmId);

    @Async("APIThreadExecutor")
    @Query(value = "SELECT L FROM Language L WHERE L.id IN (:ids)")
    CompletableFuture<Set<Language>> getLanguagesByIds(Collection<Integer> ids);
}