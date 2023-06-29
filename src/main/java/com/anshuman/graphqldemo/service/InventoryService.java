package com.anshuman.graphqldemo.service;

import com.anshuman.graphqldemo.model.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public List<Integer> getInventoryInStock(Integer filmId, Integer storeId) {
        return inventoryRepository.getInventoryInStock(filmId, storeId);
    }
}
