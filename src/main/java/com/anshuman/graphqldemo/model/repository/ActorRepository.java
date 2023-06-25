package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.Actor;
import com.anshuman.graphqldemo.model.entity.Film;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.lang.NonNull;

import java.util.List;

@RepositoryRestResource
public interface ActorRepository extends ListCrudRepository<Actor, Integer> {
    @Query("select a from Actor a inner join a.filmActors filmActors where filmActors.actor = ?1")
    List<Film> findByFilmActors_Actor(@NonNull Actor actor);
}