package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.FilmCategory;
import com.anshuman.graphqldemo.model.entity.FilmCategoryId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.scheduling.annotation.Async;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@RepositoryRestResource
public interface FilmCategoryRepository extends ListCrudRepository<FilmCategory, FilmCategoryId> {

    @Query(value = " SELECT FC " +
            " FROM FilmCategory FC " +
            " INNER JOIN FETCH FC.id I" +
            " WHERE I.filmId = :filmId")
    Set<FilmCategory> getFilmCategoriesByFilmId(Integer filmId);

    @Async("APIThreadExecutor")
    @Query(value = " SELECT FC " +
            " FROM FilmCategory FC " +
            " WHERE FC.id.filmId IN (:filmIds)")
    CompletableFuture<Set<FilmCategory>> getFilmCategoriesByFilmIds(Set<Integer> filmIds);
}