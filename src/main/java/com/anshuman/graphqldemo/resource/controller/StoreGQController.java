package com.anshuman.graphqldemo.resource.controller;

import com.anshuman.graphqldemo.resource.dto.AddressRecord;
import com.anshuman.graphqldemo.resource.dto.StoreRecord;
import com.anshuman.graphqldemo.resource.dto.StoreStaffRecord;
import com.anshuman.graphqldemo.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StoreGQController {

    private final StoreService storeService;

    @QueryMapping
    public List<StoreRecord> getStoreWithAddressByCountry(@Argument String country) {
        return storeService.getStoresByCountry(country);
    }

    @QueryMapping
    public List<StoreStaffRecord> getStaffByStoreId(@Argument Integer storeId) {
        return storeService.getStoreStaffById(storeId);
    }

    @SchemaMapping(typeName = "Store", field = "address")
    public AddressRecord address(StoreRecord store) {
        return store.address();
    }
}
