package com.anshuman.graphqldemo.model.repository;

import com.anshuman.graphqldemo.model.entity.Film;
import com.anshuman.graphqldemo.model.repository.projection.BasicFilmInfoRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface FilmRepository extends ListCrudRepository<Film, Integer> {

    @Query(value =
            " SELECT " +
                    " new com.anshuman.graphqldemo.model.repository.projection.BasicFilmInfoRecord(f.id, f.title, " +
                    " f.description, f.releaseYear, f.length) " +
                    " FROM Film f " +
                    " WHERE f.title LIKE %:name%")
    List<BasicFilmInfoRecord> customFindFilmByName(String name);

    @Query(value =
            " SELECT F " +
                    " FROM Film F " +
                    " INNER JOIN FETCH F.filmActors FA " +
                    " INNER JOIN FETCH FA.actor A " +
                    " INNER JOIN FETCH F.filmCategories FC " +
                    " INNER JOIN FETCH F.language L " +
                    " WHERE F.title LIKE %:title%")
    List<Film> customGetFilmDetailed(String title);
}