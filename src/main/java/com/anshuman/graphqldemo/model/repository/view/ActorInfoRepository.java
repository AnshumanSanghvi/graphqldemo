package com.anshuman.graphqldemo.model.repository.view;

import com.anshuman.graphqldemo.model.entity.view.ActorInfo;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RepositoryRestResource
public interface ActorInfoRepository extends ListPagingAndSortingRepository<ActorInfo, Integer> {

    @Async("APIThreadExecutor")
    @Query(" SELECT DISTINCT a " +
           " FROM ActorInfo a " +
           " WHERE a.firstName LIKE %:name% " +
           " OR a.lastName LIKE %:name% ")
    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true") })
    CompletableFuture<List<ActorInfo>> findActorByName(String name);

}