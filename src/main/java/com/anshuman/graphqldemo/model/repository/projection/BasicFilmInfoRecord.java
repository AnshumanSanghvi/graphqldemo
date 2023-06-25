package com.anshuman.graphqldemo.model.repository.projection;

/**
 * Projection for {@link com.anshuman.graphqldemo.model.entity.Film}
 */
public record BasicFilmInfoRecord(Integer id, String title, String description, Integer releaseYear, Short length) {

}