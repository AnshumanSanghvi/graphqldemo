package com.anshuman.graphqldemo.service;

import com.anshuman.graphqldemo.model.repository.AddressRepository;
import com.anshuman.graphqldemo.model.repository.projection.AddressProjection;
import com.anshuman.graphqldemo.model.repository.projection.IAddressProjection;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
@Transactional(readOnly = true, transactionManager = "JpaTransactionManager")
public class AddressService {

    private final AddressRepository addressRepository;
    @Qualifier("APIThreadExecutor") private final Executor executor;

    public AddressService(AddressRepository addressRepository, @Qualifier("APIThreadExecutor") Executor executor) {
        this.addressRepository = addressRepository;
        this.executor = executor;
    }

    @Cacheable(value = "addresses", key = "#addressId")
    public CompletableFuture<AddressProjection> gqlFindById(Integer addressId) {
        return CompletableFuture
                .supplyAsync(() -> addressRepository.gqlFindById(addressId), executor)
                .thenApply(IAddressProjection::toPojo);
    }

    public CompletableFuture<List<AddressProjection>> gqlFindAll() {
        return CompletableFuture
                .supplyAsync(addressRepository::gqlFindAll, executor)
                .thenApply(IAddressProjection::toPojoList);
    }
}
