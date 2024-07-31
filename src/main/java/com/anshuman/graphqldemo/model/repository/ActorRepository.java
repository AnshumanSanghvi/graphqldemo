package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.Actor;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.scheduling.annotation.Async;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@RepositoryRestResource
public interface ActorRepository extends ListCrudRepository<Actor, Integer> {

    @Async("APIThreadExecutor")
    @Query(" SELECT DISTINCT A " +
            " FROM Actor A " +
            " WHERE A.id IN (:ids) ")
    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true") })
    CompletableFuture<Set<Actor>> findDistinctActorByIdIn(Collection<Short> ids);
}