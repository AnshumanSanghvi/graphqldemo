package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.FilmActor;
import com.anshuman.graphqldemo.model.entity.FilmActorId;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.scheduling.annotation.Async;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@RepositoryRestResource
public interface FilmActorRepository extends ListCrudRepository<FilmActor, FilmActorId> {

    @Query(value = " SELECT FA " +
            " FROM FilmActor FA " +
            " INNER JOIN FETCH FA.id I" +
            " WHERE I.filmId = :filmId")
    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true") })
    Set<FilmActor> getActorsByFilmId(Integer filmId);

    @Async("APIThreadExecutor")
    @Query(value = " SELECT FA " +
            " FROM FilmActor FA " +
            " WHERE FA.id.filmId IN (:filmIds) ")
    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true") })
    CompletableFuture<Set<FilmActor>> getFilmActorsById(Set<Integer> filmIds);
}