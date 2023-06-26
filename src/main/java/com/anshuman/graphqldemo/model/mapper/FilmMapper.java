package com.anshuman.graphqldemo.model.mapper;

import com.anshuman.graphqldemo.model.entity.Film;
import com.anshuman.graphqldemo.resource.dto.FilmRecord;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {LanguageMapper.class, FilmCategoryMapper.class, FilmActorMapper.class, InventoryMapper.class})
public interface FilmMapper {
    Film toEntity(FilmRecord filmRecord);

    @AfterMapping
    default void linkFilmCategories(@MappingTarget Film film) {
        film.getFilmCategories().forEach(filmCategory -> filmCategory.setFilm(film));
    }

    @AfterMapping
    default void linkFilmActors(@MappingTarget Film film) {
        film.getFilmActors().forEach(filmActor -> filmActor.setFilm(film));
    }

    @AfterMapping
    default void linkInventories(@MappingTarget Film film) {
        film.getInventories().forEach(inventory -> inventory.setFilm(film));
    }

    FilmRecord toDto(Film film);

    List<FilmRecord> toDtoList(List<Film> films);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Film partialUpdate(FilmRecord filmRecord, @MappingTarget Film film);
}