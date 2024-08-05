package com.anshuman.graphqldemo.resource.dto;

import com.anshuman.graphqldemo.model.repository.projection.StoreStaffProjection;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.groupingBy;

@JsonIgnoreProperties(ignoreUnknown = true)
@Component
public record StoreStaffRecord(Integer storeId,
        String district,
        String city,
        String country,
        List<String> staffNames) {

    public static List<StoreStaffRecord> fromProjection(List<StoreStaffProjection> storeStaffProj) {
        List<StoreStaffRecord> storeStaffRecords = new ArrayList<>();

        storeStaffProj
                .stream()
                .collect(groupingBy(StoreStaffProjection::getStoreId))
                .forEach((storeId, storeProjList) -> {
                    List<String> staffNames = storeProjList.stream().map(StoreStaffProjection::getStaffName).toList();
                    StoreStaffProjection storeStaffProjection = storeProjList.getFirst();
                    String district = storeStaffProjection.getDistrict();
                    String city = storeStaffProjection.getCity();
                    String country = storeStaffProjection.getCountry();
                    storeStaffRecords.add(new StoreStaffRecord(storeId, district, city, country, staffNames));
                });

        return storeStaffRecords;
    }
}
