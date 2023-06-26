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
    public FilmCategoryIdRecord filmCategoryId(FilmCategoryRecord filmCategoryRecord) {
        return filmCategoryRecord.id();
    }

    @SchemaMapping
    public CategoryRecord category(FilmCategoryRecord filmCategoryRecord) {
        return filmCategoryRecord.categoryRecord();
    }

    @SchemaMapping
    public FilmRecord film(FilmCategoryRecord filmCategoryRecord) {
        return filmCategoryRecord.filmRecord();
    }

    @SchemaMapping
    public Set<FilmActorRecord> filmActors(FilmRecord filmRecord) {
        return filmRecord.filmActors();
    }

    @SchemaMapping
    public FilmActorIdRecord filmActorId(FilmActorRecord filmActorRecord) {
        return filmActorRecord.id();
    }

    @SchemaMapping
    public ActorRecord actor(FilmActorRecord filmActorRecord) {
        return filmActorRecord.actorRecord();
    }

    @SchemaMapping
    public FilmRecord film(FilmActorRecord filmActorRecord) {
        return filmActorRecord.filmRecord();
    }

    @SchemaMapping
    public Set<InventoryRecord> inventories(FilmRecord filmRecord) {
        return filmRecord.inventories();
    }

}
