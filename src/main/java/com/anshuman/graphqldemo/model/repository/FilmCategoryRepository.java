package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.FilmCategory;
import com.anshuman.graphqldemo.model.entity.FilmCategoryId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

@RepositoryRestResource
public interface FilmCategoryRepository extends ListCrudRepository<FilmCategory, FilmCategoryId> {

    @Query(value = " SELECT FC " +
            " FROM FilmCategory FC " +
            " INNER JOIN FETCH FC.id I" +
            " WHERE I.filmId = :filmId")
    Set<FilmCategory> getFilmCategoriesByFilmId(Integer filmId);

    @Query(value = " SELECT FC " +
            " FROM FilmCategory FC " +
            " WHERE FC.id.filmId IN (:filmIds)")
    Set<FilmCategory> getFilmCategoriesByFilmIds(Set<Integer> filmIds);
}