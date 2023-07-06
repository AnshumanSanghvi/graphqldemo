package com.anshuman.graphqldemo.model.reactive;

import com.anshuman.graphqldemo.model.entity.Country;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryReactiveRepository extends ReactiveCrudRepository<Country, Integer> {

}
