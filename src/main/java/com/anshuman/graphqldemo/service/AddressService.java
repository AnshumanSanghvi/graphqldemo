package com.anshuman.graphqldemo.service;

import com.anshuman.graphqldemo.model.repository.AddressRepository;
import com.anshuman.graphqldemo.model.repository.projection.IAddressProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AddressService {

    private final AddressRepository addressRepository;

    @Cacheable(value = "addresses", key = "#addressId")
    public IAddressProjection gqlFindById(Integer addressId) {
        return addressRepository.gqlFindById(addressId);
    }
}
