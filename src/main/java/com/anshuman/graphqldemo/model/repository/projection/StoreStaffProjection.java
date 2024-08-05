package com.anshuman.graphqldemo.model.repository.projection;


import com.anshuman.graphqldemo.model.entity.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoreStaffProjection {

    @NotNull
    private Integer storeId;
    private String district;
    private String city;
    private String country;
    private String staffName;

    public StoreStaffProjection(Store store, Address address, Staff staff) {
        this.storeId = store.getId();
        this.district = Optional.ofNullable(address)
                .map(Address::getDistrict)
                .orElse(null);
        this.city = Optional.ofNullable(address)
                .flatMap(add -> Optional.ofNullable(add.getCity()))
                .map(City::getName)
                .orElse(null);
        this.country = Optional.ofNullable(address)
                .flatMap(add -> Optional.ofNullable(add.getCity()))
                .flatMap(addrCity -> Optional.ofNullable(addrCity.getCountry()))
                .map(Country::getName)
                .orElse(null);
        this.staffName = Optional.ofNullable(staff)
                .map(st -> st.getFirstName() + " " + st.getLastName())
                .orElse(null);
    }


}
