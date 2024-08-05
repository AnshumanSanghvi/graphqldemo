package com.anshuman.graphqldemo.resource.controller;

import com.anshuman.graphqldemo.model.entity.Category;
import com.anshuman.graphqldemo.model.entity.Film;
import com.anshuman.graphqldemo.model.entity.FilmCategory;
import com.anshuman.graphqldemo.model.entity.FilmCategoryId;
import com.anshuman.graphqldemo.resource.dto.CategoryRecord;
import com.anshuman.graphqldemo.resource.dto.FilmCategoryIdRecord;
import com.anshuman.graphqldemo.resource.dto.FilmCategoryRecord;
import com.anshuman.graphqldemo.resource.dto.FilmRecord;
import com.anshuman.graphqldemo.service.CategoryService;
import com.anshuman.graphqldemo.service.FilmCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

@Controller
@RequiredArgsConstructor
public class CategoryGQController {

    private final FilmCategoryService filmCategoryService;
    private final CategoryService categoryService;

    @SchemaMapping(typeName = "Film", value = "filmCategories")
    public Set<FilmCategoryRecord> filmCategories(FilmRecord filmRecord) {
        return filmCategoryService.getFilmCategoryByFilmId(filmRecord.id());
    }

    @SchemaMapping(typeName = "FilmCategory", value = "id")
    public FilmCategoryIdRecord filmCategoryId(FilmCategoryRecord filmCategory) {
        return filmCategory.id();
    }

    @SchemaMapping(typeName = "FilmCategory", value = "category")
    public CategoryRecord category(FilmCategoryRecord filmCategory) {
        return categoryService.getCategoryRecordById(Integer.valueOf(filmCategory.id().categoryId()));
    }

    @BatchMapping(typeName = "Film2", value = "filmCategories")
    public Mono<Map<Film, Set<FilmCategory>>> filmCategories(Set<Film> films) {
        Set<Integer> filmIds = films.stream().map(Film::getId).collect(Collectors.toSet());
        return Mono.fromFuture(filmCategoryService.getCategoriesByFilmIds(filmIds))
                .map(filmCategories -> films.stream().collect(toMap(film -> film,
                        film -> getMatchingCategoriesByFilmId(filmCategories, film))));
    }

    private static Set<FilmCategory> getMatchingCategoriesByFilmId(Set<FilmCategory> filmCategories, Film film) {
        return filmCategories.stream()
                .filter(filmCategory -> Integer.valueOf(filmCategory.getId().getFilmId().intValue()).equals(film.getId()))
                .collect(Collectors.toSet());
    }

    @BatchMapping(typeName = "FilmCategory2", value = "id")
    public Mono<Map<FilmCategory, FilmCategoryId>> filmCategoryId(Set<FilmCategory> filmCategories) {
        return Mono.just(filmCategories.stream().collect(toMap(fc -> fc, FilmCategory::getId)));
    }

    @BatchMapping(typeName = "FilmCategory2", value = "category")
    public Mono<Map<FilmCategory, Category>> category(Set<FilmCategory> filmCategories) {
        Set<Short> categoryIds = filmCategories.stream().map(FilmCategory::getId).map(FilmCategoryId::getCategoryId).collect(Collectors.toSet());
        return Mono.fromFuture(categoryService.getCategoriesByIds(categoryIds))
                .map(categories -> filmCategories.stream().collect(toMap(fc -> fc,
                        fc -> getMatchingCategoriesByCatId(categories, fc))));
    }

    private static Category getMatchingCategoriesByCatId(Set<Category> categories, FilmCategory fc) {
        return categories.stream()
                .filter(category -> category.getId().equals(fc.getId().getCategoryId().intValue()))
                .findFirst()
                .orElse(new Category());
    }
}
