package com.anshuman.graphqldemo.resource.controller;

import com.anshuman.graphqldemo.model.entity.Film;
import com.anshuman.graphqldemo.resource.dto.FilmRecord;
import com.anshuman.graphqldemo.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class FilmGQController {

    private final FilmService filmService;

    @QueryMapping
    public List<FilmRecord> getFilmsDetailedByTitle(@Argument String title) {
        return filmService.getFilmsDetailedByTitle(title);
    }

    @QueryMapping
    public List<FilmRecord> getFilmsByTitle(@Argument String title) {
        return filmService.getFilmRecordsByTitle(title);
    }

    @QueryMapping
    public Set<Film> getFilms2ByTitle(@Argument String title) {
        return filmService.getFilmsByTitle(title);
    }


}
