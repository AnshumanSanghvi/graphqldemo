package com.anshuman.graphqldemo.service;

import com.anshuman.graphqldemo.model.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public List<Integer> getInventoryInStock(Integer filmId, Integer storeId) {
        return inventoryRepository.getInventoryInStock(filmId, storeId);
    }
}
