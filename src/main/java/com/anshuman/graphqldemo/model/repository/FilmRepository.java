package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.Film;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@RepositoryRestResource
public interface FilmRepository extends ListCrudRepository<Film, Integer> {

    @Async("APIThreadExecutor")
    @Query(value =
            " SELECT F " +
                    " FROM Film F " +
                    " WHERE F.title LIKE %:title%")
    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true") })
    CompletableFuture<List<Film>> customGetFilmDetailed(String title);

    @Async("APIThreadExecutor")
    @Query(value = " SELECT " +
            " NEW com.anshuman.graphqldemo.model.entity.Film( F.id, F.title, F.releaseYear, F.length, F.language ) " +
            " FROM Film F " +
            " WHERE F.title like %:title% ")
    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true") })
    CompletableFuture<Set<Film>> getFilmsByTitle(String title);
}