package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.FilmActor;
import com.anshuman.graphqldemo.model.entity.FilmActorId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

@RepositoryRestResource
public interface FilmActorRepository extends ListCrudRepository<FilmActor, FilmActorId> {

    @Query(value = " SELECT FA " +
            " FROM FilmActor FA " +
            " INNER JOIN FETCH FA.id I" +
            " WHERE I.filmId = :filmId")
    Set<FilmActor> getActorsByFilmId(Integer filmId);

    @Query(value = " SELECT FA " +
            " FROM FilmActor FA " +
            " WHERE FA.id.filmId IN (:filmIds) ")
    Set<FilmActor> getFilmActorsById(Set<Integer> filmIds);
}