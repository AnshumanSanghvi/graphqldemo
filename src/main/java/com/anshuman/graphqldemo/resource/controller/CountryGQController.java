package com.anshuman.graphqldemo.resource.controller;

import com.anshuman.graphqldemo.resource.dto.CountryRecord;
import com.anshuman.graphqldemo.service.CountryService;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class CountryGQController {
    private final CountryService countryService;

    @MutationMapping
    public CountryRecord createCountry(@Argument @NotEmpty  String name) {
        return countryService.createCountry(name);
    }

    @MutationMapping
    public Boolean deleteCountry(@Argument @NotNull Integer countryId) {
        return countryService.deleteCountry(countryId);
    }

}
