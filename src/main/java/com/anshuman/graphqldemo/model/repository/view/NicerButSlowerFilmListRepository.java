package com.anshuman.graphqldemo.model.repository.view;

import com.anshuman.graphqldemo.model.entity.view.NicerButSlowerFilmList;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface NicerButSlowerFilmListRepository extends ListPagingAndSortingRepository<NicerButSlowerFilmList, Integer> {
}