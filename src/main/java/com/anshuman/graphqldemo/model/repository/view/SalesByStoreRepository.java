package com.anshuman.graphqldemo.model.repository.view;

import com.anshuman.graphqldemo.model.entity.view.SalesByStore;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface SalesByStoreRepository extends ListPagingAndSortingRepository<SalesByStore, String> {
}