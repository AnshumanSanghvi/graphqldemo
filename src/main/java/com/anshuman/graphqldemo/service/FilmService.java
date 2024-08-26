package com.anshuman.graphqldemo.service;

import com.anshuman.graphqldemo.model.entity.Film;
import com.anshuman.graphqldemo.model.mapper.FilmMapper;
import com.anshuman.graphqldemo.model.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true, transactionManager = "JpaTransactionManager")
public class FilmService {

    private final FilmRepository filmRepository;
    private final FilmMapper filmMapper;

    @Cacheable(value = "filmWithDetails", key = "#title")
    public CompletableFuture<List<Film>> getFilmsDetailedByTitle(String title) {
        return filmRepository.customGetFilmDetailed(title);
    }

    @Cacheable(value = "films", key = "#title")
    public CompletableFuture<Set<Film>> getFilmsByTitle(String title) {
        return filmRepository.getFilmsByTitle(title);
    }

}
