package com.anshuman.graphqldemo.model.repository.view;

import com.anshuman.graphqldemo.model.entity.view.SalesByFilmCategory;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface SalesByFilmCategoryRepository extends ListPagingAndSortingRepository<SalesByFilmCategory, String> {
}