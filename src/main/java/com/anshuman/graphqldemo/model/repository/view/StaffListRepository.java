package com.anshuman.graphqldemo.model.repository.view;

import com.anshuman.graphqldemo.model.entity.view.StaffList;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface StaffListRepository extends ListPagingAndSortingRepository<StaffList, Integer> {
}