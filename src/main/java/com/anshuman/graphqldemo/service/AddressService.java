package com.anshuman.graphqldemo.service;

import com.anshuman.graphqldemo.model.repository.AddressRepository;
import com.anshuman.graphqldemo.model.repository.projection.AddressProjection;
import com.anshuman.graphqldemo.model.repository.projection.IAddressProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, transactionManager = "JpaTransactionManager")
public class AddressService {

    private final AddressRepository addressRepository;

    @Cacheable(value = "addresses", key = "#addressId")
    public IAddressProjection gqlFindById(Integer addressId) {
        return IAddressProjection.toPojo(addressRepository.gqlFindById(addressId));
    }

    public List<AddressProjection> gqlFindAll() {
        return IAddressProjection.toPojoList(addressRepository.gqlFindAll());
    }
}
