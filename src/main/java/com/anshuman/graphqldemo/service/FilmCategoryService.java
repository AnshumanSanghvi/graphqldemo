package com.anshuman.graphqldemo.service;

import com.anshuman.graphqldemo.model.entity.FilmCategory;
import com.anshuman.graphqldemo.model.mapper.FilmCategoryMapper;
import com.anshuman.graphqldemo.model.repository.FilmCategoryRepository;
import com.anshuman.graphqldemo.resource.dto.FilmCategoryRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
@Transactional(readOnly = true, transactionManager = "JpaTransactionManager")
@RequiredArgsConstructor
public class FilmCategoryService {
    private final FilmCategoryRepository filmCategoryRepository;
    private final FilmCategoryMapper filmCategoryMapper;


    @Cacheable(value = "filmCategory", key = "#filmId")
    public Set<FilmCategoryRecord> getFilmCategoryByFilmId(Integer filmId) {
        return filmCategoryMapper.toDtoSet(filmCategoryRepository.getFilmCategoriesByFilmId(filmId));
    }

    @Async("APIThreadExecutor")
    public CompletableFuture<Set<FilmCategory>> getCategoriesByFilmIds(Set<Integer> filmIds) {
        return filmCategoryRepository.getFilmCategoriesByFilmIds(filmIds);
    }
 }
