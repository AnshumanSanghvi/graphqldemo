package com.anshuman.graphqldemo.service;

import com.anshuman.graphqldemo.model.mapper.FilmCategoryMapper;
import com.anshuman.graphqldemo.model.repository.FilmCategoryRepository;
import com.anshuman.graphqldemo.resource.dto.FilmCategoryRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional(readOnly = true, transactionManager = "JpaTransactionManager")
@RequiredArgsConstructor
public class FilmCategoryService {
    private final FilmCategoryRepository filmCategoryRepository;
    private final FilmCategoryMapper filmCategoryMapper;

    public Set<FilmCategoryRecord> getFilmCategoryByFilmId(Integer filmId) {
        return filmCategoryMapper.toDtoSet(filmCategoryRepository.getFilmCategoriesByFilmId(filmId));
    }
}
