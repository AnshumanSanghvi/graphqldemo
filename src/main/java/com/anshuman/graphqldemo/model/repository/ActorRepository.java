package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.Actor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.Set;

@RepositoryRestResource
public interface ActorRepository extends ListCrudRepository<Actor, Integer> {
    @Query("select distinct a from Actor a where a.id in ?1")
    Set<Actor> findDistinctByIdIn(Collection<Short> ids);
}