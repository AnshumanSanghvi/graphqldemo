package com.anshuman.graphqldemo.resource.controller;

import com.anshuman.graphqldemo.service.InventoryService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class InventoryGQController {
    private final InventoryService inventoryService;

    @QueryMapping
    public List<Integer> getInventoryInStock(@Argument @Positive Integer filmId, @Argument @Positive Integer storeId) {
        return inventoryService.getInventoryInStock(filmId, storeId);
    }
}
