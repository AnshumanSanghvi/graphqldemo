package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.Actor;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ActorRepository extends ListCrudRepository<Actor, Integer> {
}