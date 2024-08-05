package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.Film;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Set;

@RepositoryRestResource
public interface FilmRepository extends ListCrudRepository<Film, Integer> {

    @Query(value =
            " SELECT F " +
                    " FROM Film F " +
                    " WHERE F.title LIKE %:title%")
    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true") })
    List<Film> customGetFilmDetailed(String title);

    @Query(value = " SELECT " +
            " NEW com.anshuman.graphqldemo.model.entity.Film( F.id, F.title, F.releaseYear, F.length, F.language ) " +
            " FROM Film F " +
            " WHERE F.title like %:title% ")
    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true") })
    Set<Film> getFilmsByTitle(String title);
}