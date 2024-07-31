package com.anshuman.graphqldemo.resource.controller;

import com.anshuman.graphqldemo.model.entity.Country;
import com.anshuman.graphqldemo.model.repository.projection.AddressProjection;
import com.anshuman.graphqldemo.model.repository.projection.CityProjection;
import com.anshuman.graphqldemo.service.AddressService;
import com.anshuman.graphqldemo.service.CityService;
import com.anshuman.graphqldemo.service.CountryService;
import com.anshuman.graphqldemo.util.CompletableFutureUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AddressGQController {

    private final AddressService addressService;
    private final CountryService countryService;
    private final CityService cityService;

    @QueryMapping
    public AddressProjection getAddressById(@Argument Integer addressId) {
        final String taskName = "getAddressById" + addressId;
        var projectionFuture = addressService.gqlFindById(addressId);
        return CompletableFutureUtil.withTryCatchAndTimeLimit(projectionFuture, taskName);
    }

    @QueryMapping
    public List<AddressProjection> getAddresses() {
        final String taskName = "getAddresses";
        var projectionFuture = addressService.gqlFindAll();
        return CompletableFutureUtil.withTryCatchAndTimeLimit(projectionFuture, taskName);
    }

    @SchemaMapping(typeName = "Address", value = "city")
    public CityProjection city(AddressProjection address) {
        final String taskName = "getCityById" + address.getCityId();
        var projectionFuture = cityService.gqlFindById(address.getCityId());
        return CompletableFutureUtil.withTryCatchAndTimeLimit(projectionFuture, taskName);
    }

    @SchemaMapping(typeName = "City", value = "country")
    public Country country(CityProjection city) {
        final String taskName = "getCountryById" + city.getCountryId();
        var projectionFuture = countryService.gqlFindById(city.getCountryId());
        return CompletableFutureUtil.withTryCatchAndTimeLimit(projectionFuture, taskName);
    }
}
