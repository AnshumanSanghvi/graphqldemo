package com.anshuman.graphqldemo.resource.controller;

import com.anshuman.graphqldemo.resource.dto.*;
import com.anshuman.graphqldemo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class FilmGQController {

    private final FilmService filmService;
    private final LanguageService languageService;
    private final FilmCategoryService filmCategoryService;
    private final CategoryService categoryService;
    private final FilmActorService filmActorService;
    private final ActorService actorService;

    @QueryMapping
    public List<FilmRecord> getFilmsDetailedByTitle(@Argument String title) {
        return filmService.getFilmsDetailedByTitle(title);
    }

    @QueryMapping
    public List<FilmRecord> getFilmsByTitle(@Argument String title) {
        return filmService.getFilmsByTitle(title);
    }

    @SchemaMapping(typeName = "Film", value = "language")
    public LanguageRecord language(FilmRecord filmRecord) {
        return languageService.getLanguageById(filmRecord.language().id());
    }

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
        return categoryService.getCategoryById(Integer.valueOf(filmCategory.id().categoryId()));
    }

    @SchemaMapping(typeName = "Film", value = "filmActors")
    public Set<FilmActorRecord> filmActors(FilmRecord filmRecord) {
        return filmActorService.getFilmActorByFilmId(filmRecord.id());
    }

    @SchemaMapping(typeName = "FilmActor", value = "id")
    public FilmActorIdRecord filmActorId(FilmActorRecord filmActor) {
        return filmActor.id();
    }

    @SchemaMapping(typeName = "FilmActor", value = "actor")
    public ActorRecord actor(FilmActorRecord filmActor) {
        return actorService.getByActorId(filmActor.id().actorId());
    }

}
