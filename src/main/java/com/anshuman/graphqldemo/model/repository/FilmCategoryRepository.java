package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.FilmCategory;
import com.anshuman.graphqldemo.model.entity.FilmCategoryId;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FilmCategoryRepository extends ListCrudRepository<FilmCategory, FilmCategoryId> {
}