package com.anshuman.graphqldemo.model.reactive;

import com.anshuman.graphqldemo.annotation.Reactive;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Reactive
public interface CountryReactiveRepository extends ReactiveCrudRepository<CountryReactiveEntity, Integer> {

}
