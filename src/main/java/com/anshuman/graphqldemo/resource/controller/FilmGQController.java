package com.anshuman.graphqldemo.resource.controller;

import com.anshuman.graphqldemo.model.entity.Film;
import com.anshuman.graphqldemo.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Controller
@RequiredArgsConstructor
public class FilmGQController {

    private final FilmService filmService;

    @QueryMapping
    public CompletableFuture<List<Film>> getFilmsDetailedByTitle(@Argument String title) {
        return filmService.getFilmsDetailedByTitle(title);
    }

    @QueryMapping
    public CompletableFuture<Set<Film>> getFilmsByTitle(@Argument String title) {
        return filmService.getFilmsByTitle(title);
    }

}
