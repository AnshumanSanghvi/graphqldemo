package com.anshuman.graphqldemo.resource.controller;

import com.anshuman.graphqldemo.model.entity.Film;
import com.anshuman.graphqldemo.model.entity.Language;
import com.anshuman.graphqldemo.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

@Controller
@RequiredArgsConstructor
public class LanguageGQController {

    private final LanguageService languageService;

    @BatchMapping(typeName = "Film", value = "language")
    public Mono<Map<Film, Language>> language(Set<Film> films) {
        var languageIds = films.stream().map(Film::getLanguage).collect(Collectors.toSet());
        return Mono.fromFuture(languageService.getLanguagesByIds(languageIds))
                .map(languages -> films
                        .stream()
                        .collect(toMap(film -> film, film -> getMatchingLanguages(languages, film))));
    }

    private static Language getMatchingLanguages(Set<Language> languages, Film film) {
        return languages.stream()
                .filter(language -> language.getId().equals(film.getLanguage()))
                .findFirst()
                .orElse(new Language());
    }
}
