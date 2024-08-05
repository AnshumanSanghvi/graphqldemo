package com.anshuman.graphqldemo.resource.controller;

import com.anshuman.graphqldemo.model.entity.Actor;
import com.anshuman.graphqldemo.model.entity.Film;
import com.anshuman.graphqldemo.model.entity.FilmActor;
import com.anshuman.graphqldemo.model.entity.FilmActorId;
import com.anshuman.graphqldemo.resource.dto.*;
import com.anshuman.graphqldemo.service.ActorService;
import com.anshuman.graphqldemo.service.FilmActorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

@RequiredArgsConstructor
@Controller
public class ActorGQController {

    private final ActorService actorService;
    private final FilmActorService filmActorService;
    private final HttpServletRequest request;

    @QueryMapping
    public List<ActorInfoRecord> getActorInfoByName(@Argument @PositiveOrZero Integer pageNumber,
            @Argument @PositiveOrZero Integer pageSize, @Argument @NotNull String name) {

        return Optional.ofNullable(request.getHeader("auth"))
                .filter(headerVal -> headerVal.equalsIgnoreCase("1234567"))
                .map(x -> actorService.getActorInfoByName(pageNumber, pageSize, name))
                .orElseThrow(() -> new IllegalAccessError("Missing valid authorization"));
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

    @BatchMapping(typeName = "Film2", value = "filmActors")
    public Mono<Map<Film, Set<FilmActor>>> filmActors(Set<Film> films) {
        Set<Integer> filmIds = films.stream().map(Film::getId).collect(Collectors.toSet());
        return Mono.fromFuture(filmActorService.getFilmActorsByFilmIds(filmIds))
                .map(filmActors -> films.stream().collect(toMap(film -> film,
                        film -> getMatchingActorsByFilmId(filmActors, film))));
    }

    private static Set<FilmActor> getMatchingActorsByFilmId(Set<FilmActor> filmActors, Film film) {
        return filmActors.stream()
                .filter(filmActor -> Integer.valueOf(filmActor.getFilmId().intValue()).equals(film.getId()))
                .collect(Collectors.toSet());
    }

    @BatchMapping(typeName = "FilmActor2", value = "id")
    public Mono<Map<FilmActor, FilmActorId>> filmActorId(Set<FilmActor> filmActors) {
        return Mono.just(filmActors.stream().collect(toMap(fac -> fac, FilmActor::getId)));
    }

    @BatchMapping(typeName = "FilmActor2", value = "actor")
    public Mono<Map<FilmActor, Actor>> actor(Set<FilmActor> filmActors) {
        var actorIds = filmActors.stream().map(FilmActor::getId).map(FilmActorId::getActorId).collect(Collectors.toSet());
        return Mono.fromFuture(actorService.getActorsByActorId(actorIds))
                .map(actors -> filmActors.stream().collect(toMap(filmActor -> filmActor,
                        fa -> getMatchingActorsByActorId(actors, fa))));
    }

    private static Actor getMatchingActorsByActorId(Set<Actor> actors, FilmActor fa) {
        return actors.stream()
                .filter(actor -> actor.getId().equals(fa.getId().getActorId().intValue()))
                .findFirst()
                .orElse(new Actor());
    }
}
