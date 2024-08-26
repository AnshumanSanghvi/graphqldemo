package com.anshuman.graphqldemo.resource.controller;

import com.anshuman.graphqldemo.model.entity.Country;
import com.anshuman.graphqldemo.model.repository.projection.AddressProjection;
import com.anshuman.graphqldemo.model.repository.projection.CityProjection;
import com.anshuman.graphqldemo.service.AddressService;
import com.anshuman.graphqldemo.service.CityService;
import com.anshuman.graphqldemo.service.CountryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AddressGQController {

    private final AddressService addressService;
    private final CountryService countryService;
    private final CityService cityService;

    @QueryMapping
    public CompletableFuture<AddressProjection> getAddressById(@Argument Integer addressId) {
        return addressService.gqlFindById(addressId);
    }

    @QueryMapping
    public CompletableFuture<List<AddressProjection>> getAddresses() {
        return addressService.gqlFindAll();
    }

    @SchemaMapping(typeName = "Address", value = "city")
    public CompletableFuture<CityProjection> city(AddressProjection address) {
        return cityService.gqlFindById(address.getCityId());
    }

    @SchemaMapping(typeName = "City", value = "country")
    public CompletableFuture<Country> country(CityProjection city) {
        return countryService.gqlFindById(city.getCountryId());
    }
}
