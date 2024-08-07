package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.Address;
import com.anshuman.graphqldemo.model.repository.projection.IAddressProjection;
import jakarta.persistence.QueryHint;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface AddressRepository extends ListCrudRepository<Address, Integer> {


    @NotNull
    @Query(nativeQuery = true, value = " SELECT " +
            " addr.address_id as id, " +
            " addr.address as addressLine, " +
            " addr.address2 as addressLine2, " +
            " addr.district, " +
            " addr.city_id AS cityId, " +
            " addr.postal_code AS postalCode, " +
            " addr.phone " +
            " FROM public.address addr " +
            " WHERE addr.address_id = :addressId")
    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true") })
    IAddressProjection gqlFindById(@NotNull Integer addressId);

    @Query(nativeQuery = true, value = " SELECT " +
            " addr.address_id as id, " +
            " addr.address as addressLine, " +
            " addr.address2 as addressLine2, " +
            " addr.district, " +
            " addr.city_id AS cityId, " +
            " addr.postal_code AS postalCode, " +
            " addr.phone " +
            " FROM public.address addr ")
    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true") })
    List<IAddressProjection> gqlFindAll();
}