package com.anshuman.graphqldemo.model.repository.view;

import com.anshuman.graphqldemo.model.entity.view.ActorInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ActorInfoRepository extends ListPagingAndSortingRepository<ActorInfo, Integer> {

    Page<ActorInfo> findDistinctByFirstNameLikeIgnoreCaseOrLastNameLikeIgnoreCase(Pageable page,
            String firstName, String lastName);

}