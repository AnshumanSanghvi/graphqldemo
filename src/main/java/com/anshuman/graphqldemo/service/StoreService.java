package com.anshuman.graphqldemo.service;

import com.anshuman.graphqldemo.model.mapper.StoreMapper;
import com.anshuman.graphqldemo.model.repository.StoreRepository;
import com.anshuman.graphqldemo.resource.dto.StoreRecord;
import com.anshuman.graphqldemo.resource.dto.StoreStaffRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, transactionManager = "JpaTransactionManager")
public class StoreService {

    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;

    public List<StoreRecord> getStoresByCountry(String country) {
        return storeMapper.toDtoList(storeRepository.customGetStoreWithLocation(country));
    }

    public List<StoreStaffRecord> getStoreStaffById(Integer storeId) {
        return StoreStaffRecord.fromProjection(storeRepository.customGetStoreWithStaff(storeId));
    }
}
