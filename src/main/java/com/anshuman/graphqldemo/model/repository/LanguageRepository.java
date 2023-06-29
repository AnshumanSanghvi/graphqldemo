package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.Language;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LanguageRepository extends ListCrudRepository<Language, Integer> {
}