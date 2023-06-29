package com.anshuman.graphqldemo.resource.controller;

import com.anshuman.graphqldemo.model.repository.projection.AddressProjection;
import com.anshuman.graphqldemo.model.repository.projection.CityProjection;
import com.anshuman.graphqldemo.model.repository.projection.IAddressProjection;
import com.anshuman.graphqldemo.model.repository.projection.ICityProjection;
import com.anshuman.graphqldemo.resource.dto.CountryRecord;
import com.anshuman.graphqldemo.service.AddressService;
import com.anshuman.graphqldemo.service.CityService;
import com.anshuman.graphqldemo.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AddressGQController {

    private final AddressService addressService;
    private final CountryService countryService;
    private final CityService cityService;

    @QueryMapping
    public AddressProjection getAddressById(@Argument Integer addressId) {
        var projection = addressService.gqlFindById(addressId);
        return IAddressProjection.toPojo(projection);
    }

    @SchemaMapping(typeName = "Address", value = "city")
    public CityProjection city(AddressProjection address) {
        var projection = cityService.gqlFindById(address.getCityId());
        return ICityProjection.toPojo(projection);
    }

    @SchemaMapping(typeName = "City", value = "country")
    public CountryRecord country(CityProjection city) {
        return countryService.findById(city.getCountryId());
    }
}
