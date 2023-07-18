package com.anshuman.graphqldemo.resource.controller;

import com.anshuman.graphqldemo.resource.dto.CountryRecord;
import com.anshuman.graphqldemo.service.CountryService;
import com.anshuman.graphqldemo.util.ProfileUtil;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class CountryGQController {
    private final CountryService countryService;

    @QueryMapping
    public CountryRecord getCountryById(@Argument @NotNull Integer countryId) {
        final String taskName = "getCountryById" + countryId;
        var projectionFuture = countryService.gqlFindById(countryId);
        return ProfileUtil.future(projectionFuture,taskName);
    }

    @MutationMapping
    public CountryRecord createCountry(@Argument @NotEmpty  String name) {
        return countryService.createCountry(name);
    }

    @MutationMapping
    public Boolean deleteCountry(@Argument @NotNull Integer countryId) {
        return countryService.deleteCountry(countryId);
    }

    @MutationMapping
    public CountryRecord updateCountry(@Argument @NotNull Integer id, @Argument @NotNull String name) {
        return countryService.updateCountry(id, name);
    }

}
