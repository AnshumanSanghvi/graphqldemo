package com.anshuman.graphqldemo.resource.controller;

import com.anshuman.graphqldemo.model.entity.*;
import com.anshuman.graphqldemo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

@Controller
@RequiredArgsConstructor
public class FilmGQControllerBatched {

    private final FilmService filmService;
    private final LanguageService languageService;
    private final FilmCategoryService filmCategoryService;
    private final CategoryService categoryService;
    private final FilmActorService filmActorService;
    private final ActorService actorService;

    @QueryMapping
    public Set<Film> getFilms2ByTitle(@Argument String title) {
        return filmService.getFilmsByTitle(title);
    }

    @BatchMapping(typeName = "Film2", value = "language")
    public Mono<Map<Film, Language>> language(Set<Film> films) {
        var languageIds = films.stream().map(Film::getLanguage).collect(Collectors.toSet());
        return Mono.fromFuture(languageService.getLanguagesByIds(languageIds))
                .map(langs -> films.stream()
                        .collect(toMap(film -> film,
                                film -> langs.stream()
                                        .filter(language -> language.getId().equals(film.getLanguage()))
                                        .findFirst()
                                        .orElse(new Language()))));

    }

    @BatchMapping(typeName = "Film2", value = "filmCategories")
    public Mono<Map<Film, Set<FilmCategory>>> filmCategories(Set<Film> films) {
        Set<Integer> filmIds = films.stream().map(Film::getId).collect(Collectors.toSet());
        return Mono.fromFuture(filmCategoryService.getCategoriesByFilmIds(filmIds))
                .map(fcats -> films.stream()
                        .collect(toMap(film -> film,
                        film -> fcats.stream()
                                .filter(fcat -> Integer.valueOf(fcat.getId().getFilmId().intValue()).equals(film.getId()))
                                .collect(Collectors.toSet()))));
    }

    @BatchMapping(typeName = "FilmCategory2", value = "id")
    public Mono<Map<FilmCategory, FilmCategoryId>> filmCategoryId(Set<FilmCategory> filmCategories) {
        return Mono.just(filmCategories.stream().collect(toMap(fc -> fc, FilmCategory::getId)));
    }

    @BatchMapping(typeName = "FilmCategory2", value = "category")
    public Mono<Map<FilmCategory, Category>> category(Set<FilmCategory> filmCategories) {
        Set<Short> categoryIds = filmCategories.stream().map(FilmCategory::getId).map(FilmCategoryId::getCategoryId).collect(Collectors.toSet());
        return Mono.fromFuture(categoryService.getCategoriesByIds(categoryIds))
                .map(cats -> filmCategories.stream()
                        .collect(toMap(fc -> fc,
                                fc -> cats.stream()
                                        .filter(cat -> cat.getId().equals(fc.getId().getCategoryId().intValue()))
                                        .findFirst()
                                        .orElse(new Category()))));
    }

    @BatchMapping(typeName = "Film2", value = "filmActors")
    public Mono<Map<Film, Set<FilmActor>>> filmActors(Set<Film> films) {
        Set<Integer> filmIds = films.stream().map(Film::getId).collect(Collectors.toSet());
        return Mono.fromFuture(filmActorService.getFilmActorsByFilmIds(filmIds))
                .map(fas -> films.stream()
                .collect(toMap(film -> film,
                        film -> fas.stream()
                                .filter(fa -> Integer.valueOf(fa.getFilmId().intValue()).equals(film.getId()))
                                .collect(Collectors.toSet()))));
    }


    @BatchMapping(typeName = "FilmActor2", value = "id")
    public Mono<Map<FilmActor, FilmActorId>> filmActorId(Set<FilmActor> filmActors) {
        return Mono.just(filmActors.stream().collect(toMap(fac -> fac, FilmActor::getId)));
    }

    @BatchMapping(typeName = "FilmActor2", value = "actor")
    public Mono<Map<FilmActor, Actor>> actor(Set<FilmActor> filmActors) {
        var actorIds = filmActors.stream().map(FilmActor::getId).map(FilmActorId::getActorId).collect(Collectors.toSet());
        return Mono.fromFuture(actorService.getActorsByActorId(actorIds))
                .map(acts -> filmActors.stream()
                .collect(toMap(fa -> fa,
                        fa -> acts.stream()
                                .filter(actor -> actor.getId().equals(fa.getId().getActorId().intValue()))
                                .findFirst()
                                .orElse(new Actor()))));
    }

}
