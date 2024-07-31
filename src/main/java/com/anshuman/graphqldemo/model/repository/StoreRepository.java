package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.Store;
import com.anshuman.graphqldemo.model.repository.projection.StoreStaffProjection;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface StoreRepository extends ListCrudRepository<Store, Integer> {

    @Query(value =
            " SELECT ST " +
            " FROM Store ST " +
            "   INNER JOIN FETCH ST.address ADD " +
            "   INNER JOIN FETCH ADD.city CT " +
            "   INNER JOIN FETCH CT.country CTY " +
            " WHERE CTY.country LIKE %:country% ")
    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true") })
    List<Store> customGetStoreWithLocation(String country);

    @Query(value =
            " SELECT new com.anshuman.graphqldemo.model.repository.projection.StoreStaffProjection(STO, ADD, STA) " +
            " FROM Store STO " +
            "   INNER JOIN FETCH STO.address ADD " +
            "   INNER JOIN FETCH ADD.city CT " +
            "   INNER JOIN FETCH CT.country CTY " +
            "   INNER JOIN FETCH Staff STA ON STO.id = STA.storeId " +
            " WHERE STO.id = :id")
    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true") })
    List<StoreStaffProjection> customGetStoreWithStaff(Integer id);
}