package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.FilmCategory;
import com.anshuman.graphqldemo.model.entity.FilmCategoryId;
import org.springframework.data.repository.ListCrudRepository;

public interface FilmCategoryRepository extends ListCrudRepository<FilmCategory, FilmCategoryId> {
}