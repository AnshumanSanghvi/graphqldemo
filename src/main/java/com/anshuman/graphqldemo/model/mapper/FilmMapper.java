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
    default void linkInventories(@MappingTarget Film film) {
        film.getInventories().forEach(inventory -> inventory.setFilm(film));
    }

    @Mapping(ignore = true, target = "inventories")
    FilmRecord toDto(Film film);

    List<FilmRecord> toDtoList(List<Film> films);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Film partialUpdate(FilmRecord filmRecord, @MappingTarget Film film);
}