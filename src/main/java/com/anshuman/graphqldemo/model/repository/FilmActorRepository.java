package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.FilmActor;
import com.anshuman.graphqldemo.model.entity.FilmActorId;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FilmActorRepository extends ListCrudRepository<FilmActor, FilmActorId> {
}