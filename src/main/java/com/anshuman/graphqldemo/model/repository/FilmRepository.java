package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.Film;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface FilmRepository extends ListCrudRepository<Film, Integer> {

    @Query(value =
            " SELECT F " +
                    " FROM Film F " +
                    " INNER JOIN FETCH F.filmActors FA " +
                    " INNER JOIN FETCH FA.actor A " +
                    " INNER JOIN FETCH F.filmCategories FC " +
                    " INNER JOIN FETCH FC.category C " +
                    " INNER JOIN FETCH F.language L " +
                    " WHERE F.title LIKE %:title%")
    List<Film> customGetFilmDetailed(String title);

    @Query(value = " SELECT NEW com.anshuman.graphqldemo.model.entity.Film( F.id, F.title, F.releaseYear, F.length ) " +
            " FROM Film F " +
            " WHERE F.title like %:title% ")
    List<Film> getFilmByTitle(String title);
}