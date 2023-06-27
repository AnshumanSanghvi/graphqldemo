package com.anshuman.graphqldemo.resource.controller;

import com.anshuman.graphqldemo.resource.dto.*;
import com.anshuman.graphqldemo.service.FilmService;
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

    @QueryMapping
    public List<FilmRecord> getFilmsByTitle(@Argument String title) {
        return filmService.getFilmById(title);
    }

    @SchemaMapping
    public LanguageRecord language(FilmRecord filmRecord) {
        return filmRecord.language();
    }

    @SchemaMapping
    public Set<FilmCategoryRecord> filmCategories(FilmRecord filmRecord) {
        return filmRecord.filmCategories();
    }

    @SchemaMapping
    public FilmCategoryIdRecord filmCategoryId(FilmCategoryRecord filmCategory) {
        return filmCategory.id();
    }

    @SchemaMapping
    public CategoryRecord category(FilmCategoryRecord filmCategory) {
        return filmCategory.category();
    }

    @SchemaMapping
    public FilmRecord film(FilmCategoryRecord filmCategory) {
        return filmCategory.film();
    }

    @SchemaMapping
    public Set<FilmActorRecord> filmActors(FilmRecord film) {
        return film.filmActors();
    }

    @SchemaMapping
    public FilmActorIdRecord filmActorId(FilmActorRecord filmActor) {
        return filmActor.id();
    }

    @SchemaMapping
    public ActorRecord actor(FilmActorRecord filmActor) {
        return filmActor.actor();
    }

    @SchemaMapping
    public FilmRecord film(FilmActorRecord filmActor) {
        return filmActor.film();
    }

    @SchemaMapping
    public Set<InventoryRecord> inventories(FilmRecord film) {
        return film.inventories();
    }

}
