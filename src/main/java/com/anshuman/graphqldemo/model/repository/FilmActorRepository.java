package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.FilmActor;
import com.anshuman.graphqldemo.model.entity.FilmActorId;
import org.springframework.data.repository.ListCrudRepository;

public interface FilmActorRepository extends ListCrudRepository<FilmActor, FilmActorId> {
}