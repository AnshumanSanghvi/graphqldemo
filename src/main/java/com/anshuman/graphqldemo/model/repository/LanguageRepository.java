package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.Language;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.Set;

@RepositoryRestResource
public interface LanguageRepository extends ListCrudRepository<Language, Integer> {

    @Query(value = " SELECT L " +
            " FROM Language L " +
            " INNER JOIN FETCH L.films F" +
            " WHERE F.id = :filmId")
    Language getLanguageByFilmId(Integer filmId);

    @Query(value = "SELECT L FROM Language L WHERE L.id IN (:ids)")
    Set<Language> getLanguagesByIds(Collection<Integer> ids);
}