package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.Inventory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface InventoryRepository extends ListCrudRepository<Inventory, Integer> {

    @Query(nativeQuery = true, value =
            "SELECT public.film_in_stock(:filmId, :storeId)")
    List<Integer> getInventoryInStock(Integer filmId, Integer storeId);
}